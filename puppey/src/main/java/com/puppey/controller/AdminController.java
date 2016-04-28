package com.puppey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppey.domain.News;
import com.puppey.service.CurrencyService;
import com.puppey.service.UserService;

@Controller
public class AdminController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private CurrencyService currencyService;

    @RequestMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("title", "Admin");
        model.addAttribute("message", "Admin Page - This is protected page!");
        return "/site/admin/home";
    }
    
    @RequestMapping(value = "/transactions/{userId}", method = RequestMethod.GET)
    public String viewTransactions(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("transactions", currencyService.getTransactionsByUser(userId));
        return "/transactions/admin/list";
    }
    @RequestMapping(value = "/king", method = RequestMethod.GET)
    public String newAdminPage() {
        return "king";
    }
    
    //api
    

}
