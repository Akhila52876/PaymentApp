package com.akhila.paymentapp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akhila.paymentapp.dtos.SendMoneyDTO;
import com.akhila.paymentapp.entities.BankAccountsEntity;
import com.akhila.paymentapp.entities.TransactionEntity;
import com.akhila.paymentapp.entities.UserEntity;
import com.akhila.paymentapp.entities.WalletEntity;
import com.akhila.paymentapp.repositories.BankAccountRepository;
import com.akhila.paymentapp.repositories.TransactionRepository;
import com.akhila.paymentapp.repositories.UserRepository;
import com.akhila.paymentapp.repositories.WalletRepository;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private BankAccountRepository bankAccountRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    public boolean sendMoney(SendMoneyDTO dto) {
        UserEntity sender = userRepo.findByUsername(dto.getSenderUsername());
        UserEntity receiver = userRepo.findByUsername(dto.getReceiverUsername());

        if (sender == null || receiver == null) {
            System.out.println("❌ Sender or receiver not found.");
            return false;
        }

        double amount = dto.getAmount();
        String type = dto.getDestinationType().toUpperCase();

        switch (type) {
            case "BANK":
                return handleBankToBank(dto, sender, receiver, amount);
            case "WALLET":
                if (dto.getSenderAccountNumber() != null && !dto.getSenderAccountNumber().isEmpty()) {
                    return handleBankToWallet(dto, sender, receiver, amount);
                } else {
                    return handleWalletToBank(dto, sender, receiver, amount);
                }
            default:
                System.out.println("❌ Unsupported transaction type.");
                return false;
        }
    }

    private boolean handleBankToBank(SendMoneyDTO dto, UserEntity sender, UserEntity receiver, double amount) {
        BankAccountsEntity senderBank = bankAccountRepo.findByBankAccountNo(dto.getSenderAccountNumber());
        BankAccountsEntity receiverBank = bankAccountRepo.findByBankAccountNo(dto.getReceiverAccountNumber());

        if (senderBank == null || receiverBank == null) {
            System.out.println("❌ Invalid sender or receiver bank account.");
            return false;
        }

        if (senderBank.getCurrentbalance() < amount) {
            System.out.println("❌ Insufficient funds.");
            return false;
        }

        senderBank.setCurrentbalance(senderBank.getCurrentbalance() - amount);
        receiverBank.setCurrentbalance(receiverBank.getCurrentbalance() + amount);

        bankAccountRepo.save(senderBank);
        bankAccountRepo.save(receiverBank);

        saveTransaction(sender, receiver, amount, "BANK", "DEBIT");
        saveTransaction(sender, receiver, amount, "BANK", "CREDIT");

        System.out.println("✅ Bank to Bank transfer complete.");
        return true;
    }

    private boolean handleBankToWallet(SendMoneyDTO dto, UserEntity sender, UserEntity receiver, double amount) {
        BankAccountsEntity senderBank = bankAccountRepo.findByBankAccountNo(dto.getSenderAccountNumber());
        WalletEntity receiverWallet = walletRepo.findByUser(receiver);

        if (senderBank == null || receiverWallet == null) {
            System.out.println("❌ Invalid sender bank or receiver wallet.");
            return false;
        }

        if (senderBank.getCurrentbalance() < amount) {
            System.out.println("❌ Insufficient bank balance.");
            return false;
        }

        senderBank.setCurrentbalance(senderBank.getCurrentbalance() - amount);
        receiverWallet.setBalance(receiverWallet.getBalance() + amount);

        bankAccountRepo.save(senderBank);
        walletRepo.save(receiverWallet);

        saveTransaction(sender, receiver, amount, "BANK", "DEBIT");
        saveTransaction(sender, receiver, amount, "WALLET", "CREDIT");

        System.out.println("✅ Bank to Wallet transfer complete.");
        return true;
    }

    private boolean handleWalletToBank(SendMoneyDTO dto, UserEntity sender, UserEntity receiver, double amount) {
        WalletEntity senderWallet = walletRepo.findByUser(sender);
        BankAccountsEntity receiverBank = bankAccountRepo.findByBankAccountNo(dto.getReceiverAccountNumber());

        if (senderWallet == null || receiverBank == null) {
            System.out.println("❌ Invalid sender wallet or receiver bank.");
            return false;
        }

        if (senderWallet.getBalance() < amount) {
            System.out.println("❌ Insufficient wallet balance.");
            return false;
        }

        senderWallet.setBalance(senderWallet.getBalance() - amount);
        receiverBank.setCurrentbalance(receiverBank.getCurrentbalance() + amount);

        walletRepo.save(senderWallet);
        bankAccountRepo.save(receiverBank);

        saveTransaction(sender, receiver, amount, "WALLET", "DEBIT");
        saveTransaction(sender, receiver, amount, "BANK", "CREDIT");

        System.out.println("✅ Wallet to Bank transfer complete.");
        return true;
    }

    private void saveTransaction(UserEntity sender, UserEntity receiver, double amount, String type, String transactionType) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setTransactionType(transactionType);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepo.save(transaction);
        System.out.println("💾 Saved " + transactionType + " transaction (" + type + ") for " + amount);
    }

    
    public boolean processTransaction(SendMoneyDTO dto) {
        BankAccountsEntity senderAccount = bankAccountRepo.findByBankAccountNo(dto.getSenderAccountNumber());
        BankAccountsEntity receiverAccount = bankAccountRepo.findByBankAccountNo(dto.getReceiverAccountNumber());

        if (senderAccount == null || receiverAccount == null) {
            System.out.println("Sender or Receiver account not found!");
            return false;
        }

        if (senderAccount.getCurrentbalance() < dto.getAmount()) {
            System.out.println("Insufficient funds.");
            return false;
        }

        // Deduct from sender
        senderAccount.setCurrentbalance(senderAccount.getCurrentbalance() - dto.getAmount());

        // Add to receiver
        receiverAccount.setCurrentbalance(receiverAccount.getCurrentbalance() + dto.getAmount());

        bankAccountRepo.save(senderAccount);
        bankAccountRepo.save(receiverAccount);

        // Save transaction
        TransactionEntity debitTransaction = new TransactionEntity();
        debitTransaction.setSender(senderAccount.getUser());
        debitTransaction.setReceiver(receiverAccount.getUser());
        debitTransaction.setAmount(dto.getAmount());
        debitTransaction.setType("BANK"); // or dto.getType()
        debitTransaction.setTransactionType("DEBIT");
        debitTransaction.setTimestamp(LocalDateTime.now());

        TransactionEntity creditTransaction = new TransactionEntity();
        creditTransaction.setSender(senderAccount.getUser());
        creditTransaction.setReceiver(receiverAccount.getUser());
        creditTransaction.setAmount(dto.getAmount());
        creditTransaction.setType("BANK");
        creditTransaction.setTransactionType("CREDIT");
        creditTransaction.setTimestamp(LocalDateTime.now());

        transactionRepo.save(debitTransaction);
        transactionRepo.save(creditTransaction);

        return true;
    }


}
