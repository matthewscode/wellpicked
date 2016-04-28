package com.puppey.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppey.domain.News;
import com.puppey.service.NewsService;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;
    
    //ADMIN
    @RequestMapping(value = "/admin/news/add", method = RequestMethod.GET)
    public String addNews(Model model){
        model.addAttribute("newNews", new News());
        return "/news/admin/add";
    }
    
    @RequestMapping(value = "/admin/news/add", method = RequestMethod.POST)
    public String addNewsProcess(@Valid @ModelAttribute("news") News news, BindingResult result, Model model){

        if(result.hasErrors()){
            System.out.println("errors bro");
            System.out.println(result.getErrorCount());
            System.out.println(result.getAllErrors().get(0));
            return "/news/admin/add";
        }
        
        if(!(newsService.addNews(news) == null)){
        	return "/news/admin/add";
        }
        
        return "redirect:/admin/news/confirm/" + news.getNewsId();
    }
    
    @RequestMapping(value = "/admin/news/confirm/{newsId}", method = RequestMethod.GET)
    public String addNewsConfirm(@PathVariable("newsId") int newsId, Model model){
        News newsToConfirm = newsService.getNewsById(newsId);
        model.addAttribute("newsToConfirm", newsToConfirm);
        return "/news/admin/confirm";
    }
   
    @RequestMapping(value = "/admin/news/confirm/{newsId}", method = RequestMethod.POST)
    public String addNewsConfirmProcess(@PathVariable("newsId") int newsId){
        News newsToConfirm = newsService.getNewsById(newsId);
        newsToConfirm.setConfirmed(true);
        newsService.updateNews(newsToConfirm);
        return "redirect:/admin";
    }
    
    @RequestMapping(value = "/admin/news", method = RequestMethod.GET)
    public String listNews(Model model){
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        return "/news/admin/list";
    }
    
    //api
    @RequestMapping(value = "/api/news", method = RequestMethod.POST)
    @ResponseBody
    public boolean newNews(@RequestBody News news) {
    	newsService.addNews(news);
        return true;
    }
}
