package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.ContactUsEntity;

@Repository
public interface IContactUsRepository extends JpaRepository<ContactUsEntity,Integer> {

}
