package com.akhila.paymentapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akhila.paymentapp.entities.UserEntity;

@Repository

public interface UserRepository  extends JpaRepository<UserEntity,Integer>{
  UserEntity findByUsernameAndPassword(String username,String password);
  UserEntity findByUsername(String username);
  
	 
  
}
