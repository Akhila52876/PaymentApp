package com.akhila.paymentapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akhila.paymentapp.entities.BankAccountsEntity;
import com.akhila.paymentapp.entities.UserEntity;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountsEntity, Integer> {
    
    // Custom query to get all bank accounts for a specific user
    List<BankAccountsEntity> findByUser(UserEntity user);
    List<BankAccountsEntity> findByUser_Username(String username);


}