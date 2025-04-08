package com.akhila.paymentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.service.UserService;


@Controller

public class UserRegistrationController {

	@Autowired

	  public UserService userService;
	 

	@GetMapping("/register")
	public String showRegistrationPage(Model model)
	{
		model.addAttribute("user",new UserEntity());
        System.out.println("registration page loaded");
		return "register";
				
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") UserEntity user, Model model) {
	    if (userService.existsByUsername(user.getUsername())) {
	        model.addAttribute("error", "Username already exists");
	        return "register";
	    }

	    userService.createUser(user);
	    System.out.println("Redirecting to login...");
	    return "redirect:/login";

	}

}


