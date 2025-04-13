package com.akhila.paymentapp.repositories;

import com.akhila.paymentapp.entities.TransactionEntity;
import com.akhila.paymentapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findBySenderOrReceiver(UserEntity sender, UserEntity receiver);
   

}
