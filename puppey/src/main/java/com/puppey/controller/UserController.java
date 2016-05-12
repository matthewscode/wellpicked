package com.puppey.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.puppey.domain.Comment;
import com.puppey.domain.User;
import com.puppey.service.AchievementService;
import com.puppey.service.CommentService;
import com.puppey.service.CurrencyService;
import com.puppey.service.MessageService;
import com.puppey.service.ProfileService;
import com.puppey.service.UserService;
import com.puppey.util.Message;
import com.puppey.util.SiteUser;

@Controller
@SessionAttributes("user")
@RequestMapping
public class UserController {
   

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AchievementService achievementService;
    @Autowired
    private MessageService messageService;
    

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String addUser(Model model) {
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "/user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String processAddUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model) {
        boolean checkUsername = userService.userNameExists(newUser.getUsername());
        boolean checkEmail = userService.userEmailExists(newUser.getEmail());
        if (checkUsername) {
            model.addAttribute("userNameExists", "That username is taken");
        }
        if (checkEmail) {
            model.addAttribute("emailExists", "That email is in use");
        }

        if (checkUsername || checkEmail) {
            return "/user/join";
        }

        if (result.hasErrors()) {
            return "/user/join";
        } else {
            userService.addUser(newUser);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String accountEdit(Model model) {
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(su.getUserId());
        user.setPassword("");
        model.addAttribute("user", user);
        return "/user/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String accountEditProcess(@ModelAttribute("user") User user, Model model, BindingResult result) {
        
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean checkPassword = passwordEncoder.matches(user.getPassword(), userService.getUserById(su.getUserId()).getPassword());
        boolean checkEmail = userService.userEmailExists(user.getEmail());


        if (!user.getNewPassword().isEmpty() && !user.getPassword().isEmpty()) {
            if (checkPassword) {
                user.setPassword(userService.encodePassword(user.getNewPassword()));
            } else {
                model.addAttribute("wrongPass", "Your old password is incorrect");
            }
        }

        if (!user.getEmail().equals(userService.getUserById(su.getUserId()).getEmail()) && checkEmail) {
                model.addAttribute("emailExists", "That email is already registered");
            }

        if (!model.containsAttribute("wrongPass") && !model.containsAttribute("emailExists") && !result.hasErrors()) {
            model.addAttribute("success", "You have sucessfully updated your information");
            if (user.getPassword().isEmpty()) {
                user.setPassword(userService.getUserById(su.getUserId()).getPassword());
            }
            userService.updateUser(user);
        }
        user.setPassword("");
        user.setNewPassword("");
        return "/user/account";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        
        model.addAttribute("title", "Login");
        model.addAttribute("message", "Enter your username/password:");
        return "/user/login";
    }

    //
    //ITS NOT HITTING THIS
    //
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPageProcess(HttpServletRequest req) {
        System.out.println("i got here");
        String userId = ((Integer) userService.getUserByEmail(req.getParameter("email")).getUserId()).toString();
        String password = req.getParameter("password");
        return "forward:/j_spring_security_check?username=" + userId + "&password=" + password;

    }

    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
           new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/";
     }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model) {
        model.addAttribute("error", "Username or Password are Incorrect");
        return "/user/login";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String viewOwnProfile(Model model) {
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByIdWithLists(su.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("tournamentPredictions", profileService.getTournamentPredictionsByUser(user));
        model.addAttribute("comments", commentService.getUserProfileCommentsByUser(user));
        model.addAttribute("wallComment", new Comment());
        return "/user/profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String viewOwnProfileProcess(@ModelAttribute("wallComment") Comment wallComment) {
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User commentingUser = userService.getUserById(su.getUserId());
        wallComment.setObjectName("User");
        wallComment.setObjectId(commentingUser.getUserId());
        wallComment.setUser(commentingUser);
        commentService.addComment(wallComment);
        return "redirect:/profile";
    }
    
    @RequestMapping(value = "/profile/{userId}/avatar/{achievementId}", method = RequestMethod.GET)
    public String changeAvatar(@PathVariable("achievementId") int achievementId, @PathVariable("userId") int userId){
        achievementService.changeUserAvatar(achievementId, userId);

        return "redirect:/profile";
    }
    @RequestMapping(value = "/profile/avatar/{teamSlug}", method = RequestMethod.GET)
    public String changeAvatarTeam(@PathVariable("teamSlug") String teamSlug, @ModelAttribute("userData") User user){
        user.setAvatarName(teamSlug);
        userService.updateUser(user);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
    public String displayProfileByUsername(Model model, @PathVariable("userId") int userId) {
        User user = userService.getUserByIdWithLists(userId);
        model.addAttribute("user", user);
        model.addAttribute("tournamentPredictions", profileService.getTournamentPredictionsByUser(user));
        model.addAttribute("comments", commentService.getUserProfileCommentsByUser(user));
        model.addAttribute("wallComment", new Comment());
        return "/user/profile";
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.POST)
    public String displayProfileByUsernameProcess(@PathVariable("userId") int userId,
            @ModelAttribute("wallComment") Comment wallComment) {
        SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User commentingUser = userService.getUserById(su.getUserId());
        wallComment.setObjectName("user");
        wallComment.setObjectId(userId);
        wallComment.setUser(commentingUser);
        commentService.addComment(wallComment);
        return "redirect:/profile/" + userId;
    }

    @RequestMapping(value = "/profile/transactions", method = RequestMethod.GET)
    public String viewTransactions(Model model, Principal principal) {
        model.addAttribute("transactions", currencyService.getTransactionsByUser(userService.getUserByName(principal.getName()).getUserId()));
        return "/transactions/list";
    }
    
    //API
    @ResponseBody
    @RequestMapping("/api/users/topscores/{amount}")
    public List<User> userScoresByHighest(@PathVariable("amount") int amount){
        return userService.getUsersByTopScores(amount);
    }
    
    
    
    @ResponseBody
    @RequestMapping(value = "/api/comments/submit", method=RequestMethod.POST)
    public Message submitComment(@RequestBody Comment jsonComment){
        return commentService.addComment(jsonComment);
    }
    
    @ResponseBody
    @RequestMapping(value = "/api/comments/tournamentPrediction/submit", method=RequestMethod.POST)
    public Message submitTournamentPredictionComment(@RequestBody Comment jsonComment){
        return commentService.addTournamentPredictionComment(jsonComment);
    }
    
    @ResponseBody
    @RequestMapping("/api/team/{teamId}/favorite")
    public Message favorTeam(@PathVariable("teamId") int teamId){
        return userService.addUserFavoriteTeam(teamId);
    }
    
    @ResponseBody
    @RequestMapping("/api/team/{teamId}/unfavorite")
    public Message unfavorTeam(@PathVariable("teamId") int teamId){
        return userService.removeUserFavoriteTeam(teamId);
    }
    
    @ResponseBody
    @RequestMapping("/api/user/current")
    public String loggedIn(){
    	
    	if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")){
    		return "{ \"userId\": \"0\", \"username\":  \"none\" }";
    	}else{
    		SiteUser user = (SiteUser)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    		return "{ \"userId\": \"" + user.getUserId() + "\", \"username\":  \"" + user.getUsername() +"\", \"userAvatar\": \"" + user.getUserAvatar() + "\"}";
    	}
    }
    
   
}
