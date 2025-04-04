package com.akhila.paymentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
         public UserRepository userRepository;

    
    
    
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }
}
