package com.akhila.paymentapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.akhila.paymentapp.dtos.UserDetailsDto;
import com.akhila.paymentapp.entities.BankAccountsEntity;
import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.repositories.BankAccountRepository;
import com.akhila.paymentapp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    private BankAccountsEntity bankAccountEntity;

    @Autowired
     private UserService userService;
   
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            return "redirect:/login"; // if not logged in
        }
       
        UserEntity userEntity = userService.getUserByUsername(username);
        UserDetailsDto userDto = userService.convertToDto(userEntity);
        model.addAttribute("userDto", userDto);

//        if (userDto != null) {
//            model.addAttribute("userDto", userService.convertToDto(userDto));
//        }

        List<BankAccountsEntity> accounts = bankAccountRepository.findByUser_Username(username);
        System.out.println("Accounts fetched: " + accounts);

        
        model.addAttribute("username", username);
        model.addAttribute("accounts", accounts);
 
        return "dashboard";
    }
}















