package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.ChargesEntity;

@Repository
public interface IChargesRepository extends JpaRepository<ChargesEntity, Integer> {

}
