package com.akhila.paymentapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.akhila.paymentapp.dtos.UserDetailsDto;
import com.akhila.paymentapp.entities.BankAccountsEntity;
import com.akhila.paymentapp.entities.TransactionEntity;
import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.entities.WalletEntity;
import com.akhila.paymentapp.repositories.BankAccountRepository;
import com.akhila.paymentapp.repositories.TransactionRepository;
import com.akhila.paymentapp.repositories.UserRepository;
import com.akhila.paymentapp.repositories.WalletRepository;
import com.akhila.paymentapp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;
    
    @Autowired
     private UserService userService;
   
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "redirect:/login";
        }

        UserEntity userEntity = userService.getUserByUsername(username);
        if (userEntity == null) {
            model.addAttribute("error", "User not found");
            return "error";
        }

        model.addAttribute("userDto", userService.convertToDto(userEntity));

        List<BankAccountsEntity> accounts = bankAccountRepository.findByUser_Username(username);
        model.addAttribute("accounts", accounts);

        BankAccountsEntity primaryAccount = null;
        if (!accounts.isEmpty()) {
            // Optionally: select based on isPrimary flag
            primaryAccount = accounts.get(0); 
        } else {
            System.out.println("No bank accounts found for user: " + username);
        }
        model.addAttribute("primaryAccount", primaryAccount);

        WalletEntity wallet = walletRepository.findByUser(userEntity);
        if (wallet != null) {
            model.addAttribute("walletBalance", wallet.getBalance());
        } else {
            System.out.println("No wallet found for user: " + username);
            model.addAttribute("walletBalance", 0.0);
        }

        List<TransactionEntity> transactions = transactionRepository.findBySenderOrReceiver(userEntity, userEntity);
        model.addAttribute("transactions", transactions);

        return "dashboard";
    }

}








