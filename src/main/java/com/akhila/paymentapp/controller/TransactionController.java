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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        List<BankAccountsEntity> allAccounts = bankAccountRepo.findAll();

        model.addAttribute("accounts", accounts);           // User's own accounts (sender)
        model.addAttribute("allAccounts", allAccounts);     // All accounts (receiver)
        model.addAttribute("sendMoneyDTO", new SendMoneyDTO());

        return "sendmoney";
    }

    @PostMapping("/sendmoney")
    public String sendMoney(@RequestParam(required = false) String senderAccountNumber,
                            @RequestParam(required = false) String receiverAccountNumber,
                            @RequestParam double amount,
                            @RequestParam("destinationType") String destinationType,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {

        String senderUsername = (String) session.getAttribute("username");
        if (senderUsername == null) {
            redirectAttributes.addFlashAttribute("error", "Sender not found in session.");
            return "redirect:/sendmoney";
        }

        // 🛡️ Extra validation for BANK transfer
        if (destinationType.equals("BANK") && (receiverAccountNumber == null || receiverAccountNumber.isEmpty())) {
            redirectAttributes.addFlashAttribute("error", "Receiver account number is required for BANK transfers.");
            return "redirect:/sendmoney";
        }

        String receiverUsername = senderUsername; // default to self

        if (receiverAccountNumber != null && !receiverAccountNumber.isEmpty()) {
            BankAccountsEntity receiverAccount = bankAccountRepo.findByBankAccountNo(receiverAccountNumber);
            if (receiverAccount == null || receiverAccount.getUser() == null) {
                redirectAttributes.addFlashAttribute("error", "Receiver account or user not found.");
                return "redirect:/sendmoney";
            }
            receiverUsername = receiverAccount.getUser().getUsername();
        }

        SendMoneyDTO dto = new SendMoneyDTO();
        dto.setSenderUsername(senderUsername);
        dto.setReceiverUsername(receiverUsername);
        dto.setSenderAccountNumber(senderAccountNumber);
        dto.setReceiverAccountNumber(receiverAccountNumber);
        dto.setAmount(amount);
        dto.setDestinationType(destinationType);
        System.out.println("DESTINATION TYPE RECEIVED: " + dto.getDestinationType());


        boolean success = transactionService.sendMoney(dto);

        if (success) {
            redirectAttributes.addFlashAttribute("message", "Transaction successful!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Transaction failed. Please check balance and details.");
        }

        return "redirect:/sendmoney";
    }
}
