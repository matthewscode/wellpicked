package com.puppey.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppey.domain.Team;
import com.puppey.dto.TeamDto;
import com.puppey.dto.TournamentDto;
import com.puppey.service.TeamService;
import com.puppey.service.UserService;

@Controller
@RequestMapping
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    
    String teamNameError = "teamNameExists";
    
    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public String listTeams(Model model){
        model.addAttribute("teams", teamService.getAllDisplayTeams());
        return "/team/list";
    }
    
    @RequestMapping(value = "/team/{teamSlug}", method=RequestMethod.GET)
    public String displayteam(@PathVariable("teamSlug")String teamSlug, Model model){
    	model.addAttribute("team", teamService.getTeamBySlug(teamSlug));
    	return "/team/view";
    }
    
    
    //ADMIN
    @RequestMapping(value = "/admin/team/add", method = RequestMethod.GET)
    public String addTeam(Model model) {
        Team team = new Team();
        model.addAttribute("newTeam", team);
        return "/team/admin/add";
    }

    //TO-DO needs to have @Valid
    @RequestMapping(value = "/admin/team/add", method = RequestMethod.POST)
    public String addTeamProcess(@ModelAttribute("newTeam") Team newTeam, Model model) {
    	boolean result = teamService.addTeam(newTeam);
        if (result) {
            model.addAttribute("success", "Team Added");
        } else {
            model.addAttribute(teamNameError, "Sorry that team already exists");
        }
        return "redirect:/admin/team/add";
    }

    @RequestMapping(value = "/admin/team/edit/{teamId}", method = RequestMethod.GET)
    public String editTeam(Model model, @PathVariable("teamId") int teamId) {
        Team editedTeam = teamService.getTeam(teamId);
        model.addAttribute("editedTeam", editedTeam);
        return "/team/admin/edit";
    }

    @RequestMapping(value = "/admin/team/edit/{teamId}", method = RequestMethod.POST)
    public String editTeamProcess(@Valid @ModelAttribute("editedTeam") Team editedTeam, BindingResult result,
            Model model) {
        boolean tresult = teamService.updateTeam(editedTeam);
        if (!tresult) {
            return "/team/admin/edit";
        } else if (model.containsAttribute(teamNameError)) {
            return "/team/admin/edit";
        } else {
            model.addAttribute("success", "Team information updated");
            return "/team/admin/edit";
        }
    }

    @RequestMapping(value = "/admin/teams", method = RequestMethod.GET)
    public String adminListTeams(Model model) {
        // this is only non deleted tournaments
        model.addAttribute("Teams", teamService.getAllTeams());
        return "/team/admin/list";
    }
    
    
    //API 
    @ResponseBody
    @RequestMapping("/api/tournament/team/{teamId}")
    public List<TournamentDto> tournamentsTeams(@PathVariable("teamId") int teamId) {
        return teamService.getTeamTournamentDtoList(teamId);

    }
    
    @ResponseBody
    @RequestMapping("/api/team/list/active/{numResults}")
    public List<TeamDto> getActiveTeams(@PathVariable("numResults") int numResults){
    	return teamService.getActiveTeams(numResults);
    }
}
