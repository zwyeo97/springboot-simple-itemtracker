package com.fdmgroup.ItemsTracker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Error handler that will redirect any error to the home page.
 * @author Steven
 * 
 */
@Controller
public class CustomErrorController  implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
    	//2 = fail to login
    	model.addAttribute("invalidFlag", 2);
        return "redirect:/home";
    }
     
  
}
