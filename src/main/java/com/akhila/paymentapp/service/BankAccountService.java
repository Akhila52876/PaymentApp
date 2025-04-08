package com.akhila.paymentapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhila.paymentapp.entities.BankAccountsEntity;
import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.repositories.BankAccountRepository;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountsRepository;

    // Save or update a bank account
    public BankAccountsEntity saveBankAccount(BankAccountsEntity account) {
        return bankAccountsRepository.save(account);
    }

    // Get all bank accounts for a user
    public List<BankAccountsEntity> getBankAccountsByUser(UserEntity user) {
        return bankAccountsRepository.findByUser(user);
    }

 
}
