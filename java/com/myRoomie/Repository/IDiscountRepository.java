package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.DiscountEntity;

@Repository
public interface IDiscountRepository extends JpaRepository<DiscountEntity, Integer> {

}
