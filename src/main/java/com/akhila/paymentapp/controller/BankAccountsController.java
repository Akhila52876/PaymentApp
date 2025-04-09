package com.akhila.paymentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akhila.paymentapp.dtos.AddbankAccountdto;
import com.akhila.paymentapp.entities.BankAccountsEntity;
import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.repositories.UserRepository;
import com.akhila.paymentapp.service.BankAccountService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountsController {

    @Autowired
    private BankAccountService bankaccountservice;

    @Autowired
    private UserRepository userRepo;
    
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Hello, app is working!";
    }

    @GetMapping("/addbankaccount")
    public String showBankAccount(Model model) {
        model.addAttribute("addbankAccountDto", new AddbankAccountdto());
        System.out.println("bank account loaded page");
        return "addbankaccount";
    }

    @PostMapping("/addbankaccount")
    public String addBankAccount(@ModelAttribute AddbankAccountdto bankaccount, @RequestParam("action") String action,
                                 HttpSession session,
                                 Model model) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            model.addAttribute("error", "User not logged in");
            return "login";
        }

        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "login";
        }

        BankAccountsEntity bankAccEntity = new BankAccountsEntity();
        bankAccEntity.setBankAccountNo(bankaccount.getBankAccountNo());
        bankAccEntity.setBankName(bankaccount.getBankName());
        bankAccEntity.setBranchName(bankaccount.getBranchName());
        bankAccEntity.setIfscCode(bankaccount.getIfscCode());
        bankAccEntity.setIsActive(bankaccount.getIsActive());
        bankAccEntity.setCurrentbalance(bankaccount.getCurrentbalance());
        bankAccEntity.setUser(user); // set the relation
         
        bankaccountservice.saveBankAccount(bankAccEntity);
        
        if ("saveAndClose".equals(action)) {
            return "redirect:/dashboard";
        }
        
        model.addAttribute("addbankAccountDto", new AddbankAccountdto());
        model.addAttribute("message", "Bank account added successfully.");
        return "redirect:/bankaccount/addbankaccount";

    }
    
   

}
