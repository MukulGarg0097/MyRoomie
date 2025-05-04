package com.myRoomie.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.RoomEntity;

@Repository
public interface IRoomRepository extends JpaRepository<RoomEntity,Integer> {

	List<RoomEntity> findByStartAmount(Double startAmount);

	List<RoomEntity> findByAvailabilityStatus(Boolean availabilityStatus);

	List<RoomEntity> findByMaxNoOfGuests(Integer maxNoOfGuests);

	List<RoomEntity> findByNoOfBeds(Integer noOfBeds);

	List<RoomEntity> findByIsActive(boolean b);
	
}
