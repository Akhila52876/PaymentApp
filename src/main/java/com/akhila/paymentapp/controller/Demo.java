package com.akhila.paymentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@ResponseBody
public class Demo {

	@GetMapping("/spring")
	public String main()
	{
        System.out.print("welcome");
		return "test";
				
	}
}
