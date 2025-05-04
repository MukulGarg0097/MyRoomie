package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.RoomVariantEntity;

@Repository
public interface IRoomVariantRepository extends JpaRepository<RoomVariantEntity, Integer>{

}
