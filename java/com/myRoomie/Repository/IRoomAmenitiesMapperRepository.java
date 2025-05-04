package com.myRoomie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.RoomAmenitiesMapper;

@Repository
public interface IRoomAmenitiesMapperRepository extends JpaRepository<RoomAmenitiesMapper, Integer>{
	
}