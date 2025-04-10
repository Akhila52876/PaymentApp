package com.akhila.paymentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhila.paymentapp.dtos.UserDetailsDto;
import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
         public UserRepository userRepository;
    
    public void createUser(UserEntity user) {
        userRepository.save(user);
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
