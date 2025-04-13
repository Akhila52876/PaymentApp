package com.akhila.paymentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhila.paymentapp.dtos.UserDetailsDto;
import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.entities.WalletEntity;
import com.akhila.paymentapp.repositories.UserRepository;
import com.akhila.paymentapp.repositories.WalletRepository;

@Service
public class UserService {
    
    @Autowired
         public UserRepository userRepository;
    
    @Autowired
    private WalletRepository walletRepo;
    
    public void createUser(UserEntity user) {
        UserEntity savedUser = userRepository.save(user);  // Fix: Store the saved user

        WalletEntity existingWallet = walletRepo.findByUser(savedUser);
        if (existingWallet == null) {
            WalletEntity wallet = new WalletEntity();
            wallet.setUser(savedUser);
            wallet.setBalance(0.0);
            walletRepo.save(wallet);
            System.out.println("✅ Wallet auto-created for user: " + savedUser.getUsername());
        } else {
            System.out.println("ℹ️ Wallet already exists for user: " + savedUser.getUsername());
        }
    }
    
    public UserEntity validateLogin(String username,String password)
    {
    	return userRepository.findByUsernameAndPassword(username, password);
    }
    
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public UserDetailsDto convertToDto(UserEntity userEntity) {
        UserDetailsDto dto = new UserDetailsDto();
        dto.setFullname(userEntity.getFullname());
        dto.setUsername(userEntity.getUsername());
        dto.setEmail(userEntity.getEmail());
        dto.setPhoneNumber(userEntity.getPhoneNumber());
        return dto;
    }

	
}
