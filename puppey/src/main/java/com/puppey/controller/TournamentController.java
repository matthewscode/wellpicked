package com.puppey.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppey.domain.Template;
import com.puppey.domain.Tournament;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;
import com.puppey.dto.MatchupDto;
import com.puppey.dto.TournamentDto;
import com.puppey.service.TournamentCreationService;
import com.puppey.service.TournamentPredictionService;
import com.puppey.service.TeamService;
import com.puppey.service.TournamentService;
import com.puppey.service.UserService;
import com.puppey.template.TemplateService;
import com.puppey.util.SiteUser;

@Controller
@RequestMapping
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private TournamentPredictionService tournamentPredictionService;
    @Autowired
    private TournamentCreationService tournamentCreationService;
    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public String listCurrentAndUpcomingTournaments(Model model) {
//        List<Tournament> tournaments = tournamentService.getCurrentAndUpcomingTournaments();
//        int listSize = tournaments.size();
//        if(listSize < 3){
//            model.addAttribute("oldTournaments", tournamentService.getCompletedTournaments(3 - listSize));
//        }
//        model.addAttribute("currentTournaments", tournaments);
        return "/tournament/upcoming";
    }

    @RequestMapping(value = "/tournaments/completed", method = RequestMethod.GET)
    public String listPastTournaments(Model model) {
        model.addAttribute("completedTournaments", tournamentService.getCompletedTournaments());
        return "/tournament/completed";
    }

    @RequestMapping(value = "/tournament/{tournamentSlug}", method = RequestMethod.GET)
    public String displayTournament(@PathVariable("tournamentSlug") String tournamentSlug, Model model, HttpServletRequest request) {

        Tournament selectedTournament = tournamentService.getTournamentBySlug(tournamentSlug);

        if (selectedTournament == null) {
            return "redirect:/tournaments";
        }


        model.addAttribute("selectedTournament", selectedTournament);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if(!(principal.toString().equals("anonymousUser"))){
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.getUserById(su.getUserId()); 
        model.addAttribute("currentUser", currentUser);
       }
        if (tournamentService.tournamentHasStarted(selectedTournament)) {
            model.addAttribute("predictionClosed", true);
        }

        return "/tournament/view";
    }

    @RequestMapping(value = "/tournament")
    public String redirectToList() {
        return "redirect:/tournaments";
    }
    
    //ADMIN
    
    @RequestMapping(value = "/admin/tournament/add", method = RequestMethod.GET)
    public String addTournament(Model model) {
        Tournament newTournament = new Tournament();
        // not marked as zero
        newTournament.setDeleted(0);
        model.addAttribute("newTournament", newTournament);
        model.addAttribute("templates", tournamentCreationService.getAllTemplates());
        return "/tournament/admin/add";
    }

    @RequestMapping(value = "/admin/tournament/add", method = RequestMethod.POST)
    public String processAddNewTournament(@Valid @ModelAttribute("newTournament") Tournament tournamentToBeAdded,
            BindingResult result) {
        int tournamentId = 0;
        // check if tournament name is unique
        if (!tournamentService.validateNewTournament(tournamentToBeAdded)) {
            result.rejectValue("tournamentName", "error.tournamentToBeAdded", "That tournament name already exists");
        }

        if (!tournamentService.validateNewTournamentSlug(tournamentToBeAdded)) {
            result.rejectValue("tournamentSlug", "error.tournamentToBeAdded", "That tournament slug already exists");
        }

        if (!result.hasErrors()) {
            tournamentId = tournamentService.addTournament(tournamentToBeAdded);
        }
        return "redirect:/admin/tournament/update/"+ tournamentId;

//                + tournamentService.getTournamentBySlug(tournamentToBeAdded.getTournamentSlug()).getTournamentId();

    }
    
    @RequestMapping(value = "/admin/tournament/groupstage/add", method=RequestMethod.GET)
    public String addGroupStage(Model model){
        model.addAttribute("groupStage", new Tournament());
        return "/tournament/admin/groupstageadd";
    }
    
    @RequestMapping(value = "/admin/tournament/groupstage/add", method = RequestMethod.POST)
    public String addGroupStageProcess(@Valid @ModelAttribute("groupStage") Tournament tournamentToBeAdded, BindingResult result){
        if (!tournamentService.validateNewTournament(tournamentToBeAdded)) {
            result.rejectValue("tournamentName", "error.tournamentToBeAdded", "That tournament name already exists");
        }

        if (!tournamentService.validateNewTournamentSlug(tournamentToBeAdded)) {
            result.rejectValue("tournamentSlug", "error.tournamentToBeAdded", "That tournament slug already exists");
        }

        if (!result.hasErrors()) {
            tournamentService.addTournament(tournamentToBeAdded);
        }
        return "redirect:/admin/tournament/groupstage/update/"
                + tournamentService.getTournamentBySlug(tournamentToBeAdded.getTournamentSlug()).getTournamentId();
    }

    @RequestMapping(value = "/admin/tournament/edit/{tid}", method = RequestMethod.GET)
    public String editTournament(@PathVariable("tid") int tid, Model model) {
        Tournament editedTournament = tournamentService.getTournament(tid);
        model.addAttribute("editedTournament", editedTournament);
        return "/tournament/admin/edit";
    }

    @RequestMapping(value = "/admin/tournament/edit/{tid}", method = RequestMethod.POST)
    public String processEditTournament(@Valid @ModelAttribute("editedTournament") Tournament editedTournament,
            BindingResult result) {
        // check if tournament name is unique
        if (!tournamentService.getTournament(editedTournament.getTournamentId()).getTournamentName()
                .equals(editedTournament.getTournamentName()) && !tournamentService.validateNewTournament(editedTournament)) {
                result.rejectValue("tournamentName", "error.editedTournament", "That tournament name already exists");
            }

        if (!tournamentService.getTournament(editedTournament.getTournamentId()).getTournamentSlug()
                .equals(editedTournament.getTournamentSlug()) && !tournamentService.validateNewTournamentSlug(editedTournament)) {
                result.rejectValue("tournamentSlug", "error.editedTournament", "That tournament slug already exists");
            }

        if (!result.hasErrors()) {
            tournamentService.updateTournament(editedTournament);
        }
        return "/tournament/admin/edit";
    }

    @RequestMapping(value = "/admin/tournament/update/{tournamentId}", method = RequestMethod.GET)
    public String updatedTournamentResults(@PathVariable("tournamentId") int tid, Model model) {
        Tournament tournament = tournamentService.getTournament(tid);
        if(tournament.getTemplate().getTemplateId() == 5){
            return "redirect:/admin/tournament/groupstage/update/"+tournament.getTournamentId();
        }
        model.addAttribute("teamList", teamService.getAllTeams());
        model.addAttribute("tournament", tournament);
        return "/tournament/admin/update";
    }

    @RequestMapping(value = "/admin/tournament/update/{tournamentId}", method = RequestMethod.POST)
    public String updatedTournamentResultsProcess(@ModelAttribute("tournament") Tournament tournament, BindingResult result, Model model) {

        tournamentService.updateTournamentResults(tournament);
        tournamentPredictionService.updateTournamentPredictionScores(tournament);
       
        return "redirect:/admin/tournament/update/" + tournament.getTournamentId();
    }

    @RequestMapping(value = "/admin/tournament/delete--{tid}--tournament", method = RequestMethod.GET)
    public String deleteTournamentProcess(@PathVariable int tid) {
        tournamentService.deleteTournament(tid);
        return "redirect:/admin";
    }
    
    @RequestMapping(value = "/admin/tournament/groupstage/update/{tournamentId}", method = RequestMethod.GET)
    public String updatedGroupStageResults(@PathVariable("tournamentId") int tid, Model model) {
        model.addAttribute("teamList", teamService.getAllTeams());
        model.addAttribute("tournament", tournamentService.getTournament(tid));
        return "/tournament/admin/groupstageupdate";
    }
    
    @RequestMapping(value = "/admin/tournament/groupstage/update/{tournamentId}", method = RequestMethod.POST)
    public String updatedGroupStageResultsProcess(@ModelAttribute("tournament") Tournament tournament) {
        // this need to save matchups in the tournament, not the tournament
        // itself, implemented in service
        tournamentService.updateGroupStageResults(tournament);
        tournamentPredictionService.updateTournamentPredictionScores(tournament);
        // place winners
        return "redirect:/admin/tournament/groupstage/update/" + tournament.getTournamentId();
    }

    @RequestMapping(value = "/admin/tournaments", method = RequestMethod.GET)
    public String adminListTournaments(Model model) {
        // this is only non deleted tournaments
        model.addAttribute("Tournaments", tournamentService.getAllTournamentsByCreationDate());
        return "/tournament/admin/list";
    }
    
    //API
    @ResponseBody
    @RequestMapping("/api/tournament/all")
    public List<Tournament> tournaments() {
        return tournamentService.getAllTournaments();
    }
    
    @ResponseBody
    @RequestMapping("/api/tournament/{id}")
    public TournamentDto tournament(@PathVariable int id) {
        return tournamentService.getTournamentDto(id);
    }
    
    @ResponseBody
    @RequestMapping("/api/tournament/{tournamentId}/predictions/top/{amount}")
    public List<TournamentPrediction> tournamentTopPredictionsForTournament(
            @PathVariable("tournamentId") int tournamentId, @PathVariable("amount") int amount) {
        return tournamentPredictionService.getTopPredictionsForTournament(tournamentId, amount);
    }
    
    @ResponseBody
    @RequestMapping("/api/tournament/list/latest/{numberOfResults}")
    public List<TournamentDto> lastestTournamentList(@PathVariable("numberOfResults") int numResults){
    	return tournamentService.getLatestTournaments(numResults);
    }
    
    
    //ADMIN API
    @ResponseBody
    @RequestMapping(value = "/admin/api/tournament/matchups/{tournamentId}")
    public List<MatchupDto> matchupDtosForTournament(@PathVariable("tournamentId") int tournamentId){
    	
    	return tournamentService.getMatchupListDto(tournamentId);
    }
    
    @ResponseBody
    @RequestMapping(value = "/admin/api/tournament/matchup/update/{matchupId}/{teamInt}/{teamId}", method = RequestMethod.POST)
    public boolean updateMatchupTeam(@PathVariable("matchupId") int matchupId, @PathVariable("teamInt") int teamInt, @PathVariable("teamId") int teamId){
    	return tournamentService.updateMatchupTeam(matchupId, teamInt, teamId);
    }
    
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Template.class, "template", new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) {
            Template template = templateService.getTemplateById(Integer.parseInt(text));
            setValue(template);
        }
        });
    }
}
