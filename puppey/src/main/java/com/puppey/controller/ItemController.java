package com.puppey.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puppey.domain.Item;
import com.puppey.service.ItemService;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    //ADMIN
    @RequestMapping(value = "/admin/item/add", method = RequestMethod.GET)
    public String addItem(Model model){
        model.addAttribute("newItem", new Item());
        return "/item/admin/add";
    }
    
    @RequestMapping(value = "/admin/item/add", method = RequestMethod.POST)
    public String addItemProcess(@Valid @ModelAttribute("newItem") Item newItem,
            BindingResult result) {
        if (result.hasErrors()) {
            return "item/admin/add";
        }
        itemService.addItem(newItem);
        return "/item/admin/add";
    }
    
    @RequestMapping(value = "/admin/items", method = RequestMethod.GET)
    public String listItems(Model model){
        model.addAttribute("items", itemService.getAllItems());
        return "/item/admin/list";
    }
}
