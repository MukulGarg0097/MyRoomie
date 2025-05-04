package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.TransactionEntity;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Integer> {

}
