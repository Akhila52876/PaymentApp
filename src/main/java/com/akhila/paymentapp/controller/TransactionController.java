package com.akhila.paymentapp.controller;

import com.akhila.paymentapp.dtos.SendMoneyDTO;
import com.akhila.paymentapp.entities.BankAccountsEntity;
import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.repositories.BankAccountRepository;
import com.akhila.paymentapp.repositories.UserRepository;
import com.akhila.paymentapp.service.TransactionService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private BankAccountRepository bankAccountRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/sendmoney")
    public String showSendMoneyForm(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        UserEntity user = userRepo.findByUsername(username);
        List<BankAccountsEntity> accounts = bankAccountRepo.findByUser(user);

        model.addAttribute("accounts", accounts);

        List<BankAccountsEntity> allAccounts = bankAccountRepo.findAll();
        model.addAttribute("allAccounts", allAccounts); // NEW - for receiver

        model.addAttribute("sendMoneyDTO", new SendMoneyDTO());
        return "sendmoney";
    }

    @PostMapping("/sendmoney")
    public String sendMoney(@RequestParam String senderAccountNumber,
                            @RequestParam String receiverAccountNumber,
                            @RequestParam double amount,
                            @RequestParam String destinationType,
                            HttpSession session,
                            Model model) {

        String senderUsername = (String) session.getAttribute("username");
        if (senderUsername == null) {
            return "redirect:/login";
        }

        UserEntity senderUser = userRepo.findByUsername(senderUsername);
        UserEntity receiverUser = bankAccountRepo.findByBankAccountNo(receiverAccountNumber).getUser();

        SendMoneyDTO dto = new SendMoneyDTO();
        dto.setSenderUsername(senderUsername);
        dto.setReceiverUsername(receiverUser.getUsername());
        dto.setSenderAccountNumber(senderAccountNumber);
        dto.setReceiverAccountNumber(receiverAccountNumber);
        dto.setAmount(amount);
        dto.setType(destinationType);

        boolean success = transactionService.processTransaction(dto);

        if (success) {
            model.addAttribute("message", "Transaction successful!");
        } else {
            model.addAttribute("error", "Transaction failed. Please check balance and details.");
        }

        return "redirect:/sendmoney";
    }
}
