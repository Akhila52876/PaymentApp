package com.akhila.paymentapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akhila.paymentapp.entities.UserEntity;

public interface UserRepository  extends JpaRepository<UserEntity,Integer>{
  UserEntity findByEmail(String email);
	
}
