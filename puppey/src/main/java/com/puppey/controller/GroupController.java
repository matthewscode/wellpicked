package com.puppey.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppey.domain.Group;
import com.puppey.domain.Item;
import com.puppey.domain.Prize;
import com.puppey.domain.Tournament;
import com.puppey.domain.User;
import com.puppey.service.GroupService;
import com.puppey.service.ItemService;
import com.puppey.service.TournamentPredictionService;
import com.puppey.service.TournamentService;
import com.puppey.service.UserService;

@Controller
public class GroupController {
    //TO-DO COME BACK TO THIS AFTER JEN HAS HER GROUP API SORTED OUT

    @Autowired
    private GroupService groupService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TournamentPredictionService tournamentPredictionService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/pools", method = RequestMethod.GET)
    public String groupHome(Model model) {
        model.addAttribute("groupList", groupService.getFeaturedGroups());
        model.addAttribute("currentTournaments", tournamentService.getActiveTournaments());
        return "/group/list";
    }

    @RequestMapping(value = "/pool/create", method = RequestMethod.GET)
    public String addGroup(Model model) {
        Group newGroup = new Group();
        model.addAttribute("newGroup", newGroup);
        model.addAttribute("activeTournaments", tournamentService.getActiveTournaments());
        return "/group/add";
    }

    @RequestMapping(value = "/pool/create", method = RequestMethod.POST)
    public String addGroupProcess(@Valid @ModelAttribute("newGroup") Group newGroup, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().get(0).toString());//TO-DO log this not sysout
            return "group/add";
        }
        groupService.addGroup(newGroup);
        return "redirect:/pool/" + newGroup.getGroupId();
    }

    @RequestMapping(value = "/pool/{groupId}", method = RequestMethod.GET)
    public String viewGroup(@PathVariable("groupId") int groupId, Model model) {
        model = groupService.viewGroupModels(groupId, model);
        return "group/view";
    }

    @RequestMapping(value = "/pool/{groupId}/join", method = RequestMethod.GET)
    public String joinGroupGet(@PathVariable("groupId") int groupId) {
    	
            groupService.addUserToGroup(groupId);
            
        return "redirect:/pool/" + groupId;
    }

    @RequestMapping(value = "/pool/{groupId}/addPrediction", method = RequestMethod.POST)
    public String addTournamentPrediction(@RequestParam("tpid") int tournamentPredictionId, @PathVariable("groupId") int groupId) {
        groupService.addTournamentPredictionToGroup(tournamentPredictionId, groupId);
        return "redirect:/pool/" + groupId;
    }
    
    //API  
    @ResponseBody
    @RequestMapping("/api/group/")
    public List<Group> searchGroups(@RequestParam(value = "featured", required=false) boolean featured, @RequestParam(value = "tournamentId", required=false) Integer tournamentId){
        if(tournamentId == null){
            return new ArrayList<Group>();
        }
        return groupService.searchGroups(featured, tournamentId);
    }

    //ADMIN
    @RequestMapping(value = "/admin/pool/add", method = RequestMethod.GET)
    public String addAdminGroup(Model model) {
        Group newGroup = new Group();
        newGroup.setEntryCost(5000);
        List<Tournament> activeTournaments = tournamentService.getActiveTournaments();
        model.addAttribute("newGroup", newGroup);
        model.addAttribute("activeTournaments", activeTournaments);
        return "/group/admin/add";
    }

    @RequestMapping(value = "/admin/pool/add", method = RequestMethod.POST)
    public String addAdminGroupProcess(@Valid @ModelAttribute("newGroup") Group newGroup, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().get(0).toString());
            return "group/admin/add";
        }
        groupService.addGroup(newGroup);
        return "redirect:/admin/pool/add";
    }
    
    @RequestMapping(value = "/admin/pool/edit/{id}", method = RequestMethod.GET)
    public String editGroup(@PathVariable("id") int groupId, Model model) {
        model.addAttribute("group", groupService.getGroupById(groupId));
        
        return "/group/admin/edit";
    }
    
    @RequestMapping(value = "/admin/pool/edit/{id}", method = RequestMethod.POST)
    public String editGroupProcess(@Valid @ModelAttribute("group") Group group, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Errors: " +result.getErrorCount());
            System.out.println(result.getAllErrors().get(0));
           return "/group/admin/edit";
        }
        System.out.println(group.getGroupName());
        groupService.updateGroup(group);
        return "redirect:/admin/pool/edit/"+ group.getGroupId();
    }
    
    @RequestMapping(value = "/admin/pools", method = RequestMethod.GET)
    public String adminListGroups(Model model) {
        model.addAttribute("Groups", groupService.getAllGroups());
        return "/group/admin/list";
    }
    
    @RequestMapping(value = "/admin/pool/{id}/prizes", method = RequestMethod.GET)
    public String displayPrizes(Model model, @PathVariable("id") int groupId){
        Prize newPrize = new Prize();
        Group group = groupService.getGroupById(groupId);
        List<Item> itemList = itemService.getAllItems();
        newPrize.setUserGroup(group);
        model.addAttribute("group", group);
        model.addAttribute("newPrize", newPrize);
        model.addAttribute("itemList", itemList);
        return "/group/admin/prizes";
    }
    
    @RequestMapping(value = "/admin/pool/{id}/prizes", method = RequestMethod.POST)
    public String addPrizeProcess(@Valid @ModelAttribute("newPrize") Prize newPrize, BindingResult result, Model model){
        if (result.hasErrors()) {
            System.out.println("errors");
            System.out.println(result.getAllErrors().get(0));
            return "/group/admin/prizes";
        }
        groupService.updatePrize(newPrize);
        return "/group/admin/prizes";
    }
    
    @RequestMapping(value="/admin/prize/{id}/edit", method = RequestMethod.GET)
    public String editPrize(@PathVariable("id") int prizeId, Model model){
        Prize prize = groupService.getPrize(prizeId);
        model.addAttribute("prize", prize);
        model.addAttribute("itemList", itemService.getAllItems());
        return "/group/admin/editPrize";
    }
    
    @RequestMapping(value="/admin/prize/{id}/edit", method = RequestMethod.POST)
    public String editPrize(@ModelAttribute("prize") Prize prize, BindingResult result, Model model){
        if(result.hasErrors()){
            
            return "/group/admin/editPrize";
        }
        groupService.updatePrize(prize);
        
        return "redirect:/admin/prize/"+prize.getPrizeId()+"/edit";
    }
    
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Item.class, "item", new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) {
            Item item = itemService.getItemById(Integer.parseInt(text));
            setValue(item);
        }
        });
        
        binder.registerCustomEditor(Tournament.class, "tournamentList", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Tournament tournament = tournamentService.getTournament(Integer.parseInt(text));
                setValue(tournament);
            }
            });
        
        binder.registerCustomEditor(User.class, "user", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                User user = userService.getUserById(Integer.parseInt(text));
                setValue(user);
            }
            });
        
        binder.registerCustomEditor(Group.class, "userGroup", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Group userGroup = groupService.getGroupById(Integer.parseInt(text));
                setValue(userGroup);
            }
            });
    }

}
