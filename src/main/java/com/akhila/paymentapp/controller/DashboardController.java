package com.akhila.paymentapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.akhila.paymentapp.entities.BankAccountsEntity;
import com.akhila.paymentapp.repositories.BankAccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    private BankAccountsEntity bankAccountEntity;
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            return "redirect:/login"; // if not logged in
        }

        List<BankAccountsEntity> accounts = bankAccountRepository.findByUser_Username(username);
        System.out.println("Accounts fetched: " + accounts);

        model.addAttribute("username", username);
        model.addAttribute("accounts", accounts);
        return "dashboard";
    }
}
