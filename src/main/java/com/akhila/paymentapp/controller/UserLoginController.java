package com.akhila.paymentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.entities.WalletEntity;
import com.akhila.paymentapp.repositories.UserRepository;
import com.akhila.paymentapp.repositories.WalletRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserLoginController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WalletRepository walletRepo;

    @GetMapping("/login")
    public String showLoginPage() {
        System.out.println("🔓 Login page loaded");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        UserEntity user = userRepo.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("username", user.getUsername());

            // ✅ Wallet check or auto-create
            WalletEntity wallet = walletRepo.findByUser(user);
            if (wallet == null) {
                wallet = new WalletEntity();
                wallet.setUser(user);
                wallet.setBalance(0.0);
                walletRepo.save(wallet);
                System.out.println("🛠 Wallet auto-created for user: " + user.getUsername());
            } else {
                System.out.println("✅ Wallet found: Balance = " + wallet.getBalance());
            }

            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
