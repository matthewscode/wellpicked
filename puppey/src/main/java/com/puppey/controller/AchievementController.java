package com.puppey.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puppey.domain.Achievement;
import com.puppey.domain.Artist;
import com.puppey.service.AchievementService;

@Controller
public class AchievementController {
    
    @Autowired
    private AchievementService achievementService;
    

    @RequestMapping(value = "/achievements", method = RequestMethod.GET)
    public String listAchievementsPublic(Model model) {
        model.addAttribute("achievements", achievementService.getAllAchievements());
        return "/achievement/list";
    }

    //admin
    @RequestMapping(value = "/admin/achievement/add", method = RequestMethod.GET)
    public String addAchievement(Model model) {
        Achievement achievement = new Achievement();
        model.addAttribute("achievement", achievement);
        return "/achievement/admin/add";
    }

    @RequestMapping(value = "/admin/achievement/add", method = RequestMethod.POST)
    public String addAchievementProcess(@Valid @ModelAttribute("achievement") Achievement achievement,
            BindingResult result) {
        if (result.hasErrors()) {
            return "achievement/admin/add";
        }
        achievementService.createAchievement(achievement);
        return "/achievement/admin/add";
    }

    @RequestMapping(value = "/admin/achievements", method = RequestMethod.GET)
    public String listAchievements(Model model) {
        model.addAttribute("achievements", achievementService.getAllAchievements());
        return "/achievement/admin/list";
    }

    @RequestMapping(value = "/admin/achievements", method = RequestMethod.POST)
    public String updateAchievements(Model model) {
        model.addAttribute("achievements", achievementService.getAllAchievements());
        return "/achievement/admin/list";
    }

    @RequestMapping(value = "/admin/achievement/edit/{achievementId}", method = RequestMethod.GET)
    public String editAchievement(@PathVariable("achievementId") int achievementId, Model model) {
        Achievement achievement = achievementService.getAchievementById(achievementId);
        model.addAttribute("achievement", achievement);
        return "/achievement/admin/edit";
    }

    @RequestMapping(value = "/admin/achievement/edit/{achievementId}", method = RequestMethod.POST)
    public String editAchievementProcess(@Valid @ModelAttribute("achievement") Achievement achievement,
            BindingResult result) {
        if (result.hasErrors()) {
            return "achievement/admin/edit";
        }
        achievementService.updateAchievement(achievement);
        return "/achievement/admin/edit";
    }
    
    @RequestMapping(value = "/admin/achievement/artist/add", method = RequestMethod.GET)
    public String addArtist(Model model){
    	model.addAttribute("artist", new Artist());
    	return "/artist/admin/add";
    }
    
    @RequestMapping(value = "/admin/achievement/artist/add", method = RequestMethod.POST)
    public String addArtistProcess(@Valid @ModelAttribute("artist") Artist artist, BindingResult result){
    	
    	return "/artist/admin/add";
    }

}
