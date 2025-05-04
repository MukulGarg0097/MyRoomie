package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.PropertyTransactionEntity;

@Repository
public interface IPropertyTransactionRepository extends JpaRepository<PropertyTransactionEntity, Integer> {

}
