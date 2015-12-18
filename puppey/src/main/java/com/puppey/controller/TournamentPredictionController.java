package com.puppey.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppey.domain.Tournament;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;
import com.puppey.dto.TournamentPredictionDto;
import com.puppey.service.MessageService;
import com.puppey.service.TournamentPredictionService;
import com.puppey.service.TeamService;
import com.puppey.service.TournamentService;
import com.puppey.service.UserService;
import com.puppey.util.Message;
import com.puppey.util.Utility;

@Controller
@RequestMapping
public class TournamentPredictionController {

    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private TournamentPredictionService tournamentPredictionService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/bracket/{tournamentPredictionId}", method = RequestMethod.GET)
    public String userTournamentPrediction(@PathVariable("tournamentPredictionId") int tournamentPredictionId,
            Model model) {

        TournamentPrediction tournamentPrediction = tournamentPredictionService
                .getTournamentPredictionById(tournamentPredictionId);

        if (tournamentPrediction != null) {
            model.addAttribute("userPrediction", tournamentPrediction);
        }

        return "/bracket/view";

    }
    
    @RequestMapping(value = "/bracket/{tournamentSlug}/new", method = RequestMethod.GET)
    public String predictTournamentMatchups(@PathVariable("tournamentSlug") String tournamentSlug, Model model,
            Principal principal) {
        User user = userService.getUserByName(principal.getName());
        Tournament tournament = tournamentService.getTournamentBySlug(tournamentSlug);
        if (tournament == null) {
            return "redirect:/tournaments";
        }
        if (tournamentService.tournamentHasStarted(tournament)) {
            if (tournamentPredictionService.userPredictionsForTournamentExists(user, tournament)) {
                return "redirect:/tournament/" + tournamentSlug + "/" + user.getNewUsername();
            } else {
                model.addAttribute("predictionClosed", true);
                return "/bracket/new";
            }
        } else {
            TournamentPrediction tournamentPrediction = new TournamentPrediction();
            tournamentPrediction.setUser(user);
            tournamentPrediction.setTournament(tournament);
            model.addAttribute("teamList", teamService.getAllTeams());
            model.addAttribute("userTournamentPrediction", tournamentPrediction);
            return "/bracket/new";
        }
    }
    
    @RequestMapping(value = "/bracket/{tournamentPredictionId}/edit", method = RequestMethod.GET)
    public String editUserTournamentBracket(@PathVariable("tournamentPredictionId") int tournamentPredictionId, @ModelAttribute("userData") User currentUser, Model model){
    	TournamentPrediction tournamentPrediction = tournamentPredictionService.getTournamentPredictionById(tournamentPredictionId);
    	System.out.println(currentUser.getUsername());
    	if(tournamentPrediction.getUser().getUserId() == currentUser.getUserId() && tournamentPrediction.getTournament().getTournamentStart() > Utility.getCurrentTime()){
    		model.addAttribute("userTournamentPrediction", tournamentPrediction);
    		return "/bracket/edit";
    	}
    	model.addAttribute("tournamentStarted", "You cannot edit this bracket anymore!");
    	return "redirect:/bracket/" + tournamentPredictionId;
    }
    
    @RequestMapping(value = "/brackets", method = RequestMethod.GET)
    public String viewBrackets(Model model) {
        model.addAttribute("tournaments", tournamentService.getActiveTournaments());
        return "/bracket/list";

    }
    
    @RequestMapping("/hallofFame")
    public String viewHallfOfFame(Model model){
    	return "/bracket/halloffame";
    }
    
    //API
    
    @ResponseBody
    @RequestMapping("/api/tournament/{tournamentId}/predictions/latest/{amount}")
    public List<TournamentPredictionDto> tournamentLatestPredictionsForTournament(
            @PathVariable("tournamentId") int tournamentId, @PathVariable("amount") int amount) {
        return tournamentPredictionService.getLatestPredictionsForTournament(tournamentId, amount);
    }
    
    @ResponseBody
    @RequestMapping("/api/tournament/{tournamentId}/predictions/popular/{amount}")
    public List<TournamentPredictionDto> tournamentPopularPredictionsForTournament(
            @PathVariable("tournamentId") int tournamentId, @PathVariable("amount") int amount) {
        return tournamentPredictionService.getPopularPredictionsForTournament(tournamentId, amount);
    }
    
    //predictions for tournament by userid
    @ResponseBody
    @RequestMapping("/api/tournament/{tournamentId}/predictions/user/{userId}")
    public List<TournamentPredictionDto> tournamentPredictionsForTournamentByUser(@PathVariable int tournamentId, @PathVariable int userId){
        return tournamentPredictionService.getTournamentPredictionForTournamentByUser(tournamentId, userId);
    }
    
    @ResponseBody
    @RequestMapping("/api/prediction/user/{id}")
    public List<TournamentPrediction> tournamentPredictionsByUser(@PathVariable int id) {
        return tournamentPredictionService.getTournamentPredictionsByUser(userService.getUserById(id));
    }

    @ResponseBody
    @RequestMapping("/api/prediction/{id}")
    public TournamentPredictionDto tournamentPrediction(@PathVariable int id) {
        return tournamentPredictionService.getTournamentPredictionDtoById(id);
    }
    
    @ResponseBody
    @RequestMapping(value = "/api/predictions/save", method = RequestMethod.POST)
    public Message updatePrediction(@RequestBody TournamentPrediction jsonTP) {
        if(jsonTP.getTournamentPredictionId() > 0){
            return tournamentPredictionService.updateApiTournamentPrediction(jsonTP);
        }else{
            return tournamentPredictionService.createApiTournamentPrediction(jsonTP);
        }
        
    }
    
}
