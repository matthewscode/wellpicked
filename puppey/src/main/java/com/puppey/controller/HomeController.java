package com.puppey.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppey.domain.Comment;
import com.puppey.domain.Tournament;
import com.puppey.domain.User;
import com.puppey.dto.StreamDto;
import com.puppey.service.AchievementService;
import com.puppey.service.CommentService;
import com.puppey.service.StreamService;
import com.puppey.service.StreamServiceImpl;
import com.puppey.service.TeamService;
import com.puppey.service.TournamentService;
import com.puppey.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private AchievementService achievementService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private StreamService streamService;
    @Autowired
    private TournamentService tournamentService;
    
    @RequestMapping
    public String list() {
        return "index";
    }

    @RequestMapping("/achievements")
    public String listAchievements(Model model) {
        model.addAttribute("achievements", achievementService.getAllAchievements());
        return "/achievement/list";
    }
    
    @RequestMapping("/stream/{twitch}")
    public String streamPage(@PathVariable("twitch") String twitch, Model model){
        model.addAttribute("twitch", twitch);
        return "/site/stream";
    }
    
    //API

    
    @ResponseBody
    @RequestMapping("/api/comments/tournamentPrediction/{tournamentPredictionId}")
    public List<Comment> commentsList(@PathVariable("tournamentPredictionId") int objectId){
        return commentService.getCommentsByTournamentPrediction(objectId);
    }
    
    @ResponseBody
    @RequestMapping("/api/comments/{objectName}/{objectId}")
    public List<Comment> commentsList(@PathVariable("objectName") String objectName, @PathVariable("objectId") int objectId){
        return commentService.getCommentsByObjectAndId(objectName, objectId);
    }
    
    
    
    @ResponseBody
    @RequestMapping("/api/streams/team/{teamId}")
    public JSONObject teamStreams(@PathVariable("teamId") int teamId){
        return streamService.getStreamsByTeam(teamId);
        
    }
    
    //TO-DO this needs to be replaced with queue chache
    @ResponseBody
    @RequestMapping("streams/tournaments")
    public List<JSONObject>  liveTournamentStreams(){
        List<JSONObject> finalJson = new ArrayList<>();
        List<Tournament> tournaments = tournamentService.getCurrentTournaments();
        for(Tournament tournament : tournaments){
            JSONObject tournamentStreamObject = null;
            String url = "https://api.twitch.tv/kraken/streams/" + tournament.getTwitchTag();
            try{
                String streamJson = IOUtils.toString(new URL(url).openStream(), "UTF-8");
                tournamentStreamObject = (JSONObject) JSONValue.parseWithException(streamJson);
                if(tournamentStreamObject.get("stream") != null){
                    finalJson.add(tournamentStreamObject);
                }
            }catch (Exception e){
                
            }
            
        }
        return finalJson;
        
    }
	@ResponseBody
	@RequestMapping("/api/streams/live")
	public List<StreamDto> getStreamList(){
	        return StreamServiceImpl.currentStreams;  
	    }
}
