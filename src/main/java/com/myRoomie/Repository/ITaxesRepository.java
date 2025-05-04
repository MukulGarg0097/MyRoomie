package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.TaxesEntity;

@Repository
public interface ITaxesRepository extends JpaRepository<TaxesEntity, Integer> {

}
