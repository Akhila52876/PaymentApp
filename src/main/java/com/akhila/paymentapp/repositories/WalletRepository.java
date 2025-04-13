package com.akhila.paymentapp.repositories;

import com.akhila.paymentapp.entities.WalletEntity;
import com.akhila.paymentapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    WalletEntity findByUser(UserEntity user);
    
    WalletEntity findByWalletId(Long walletId);
}

