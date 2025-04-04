package com.akhila.paymentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class UserRegistrationController {

	@GetMapping("/register")
	public String main()
	{
        System.out.print("welcome");
		return "register";
				
	}
}


