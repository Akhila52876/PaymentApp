package com.akhila.paymentapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.akhila.paymentapp.entities.TransactionEntity;
import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.repositories.TransactionRepository;
import com.akhila.paymentapp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewTransactionsController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public String showTransactionHistory(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        UserEntity user = userService.getUserByUsername(username);
        List<TransactionEntity> transactions = transactionRepository.findBySenderOrReceiver(user, user);
        model.addAttribute("transactions", transactions);
        return "transactions"; // Ensure this maps to transaction.jsp
    }
}
