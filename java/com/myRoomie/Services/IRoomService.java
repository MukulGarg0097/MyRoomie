package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.RoomRequest;
import com.myRoomie.response.RoomResponse;

public interface IRoomService {

	List<RoomResponse> findByStartAmount(Double startAmount) throws BaseException;
	List<RoomResponse> findByAvailabilityStatus(Boolean availabilityStatus) throws BaseException;
	List<RoomResponse> findByMaxNoOfGuests(Integer maxNoOfGuests) throws BaseException;
	List<RoomResponse> findByNoOfBeds(Integer noOfBeds) throws BaseException;
	List<RoomResponse> findAll() throws BaseException;
	RoomResponse findById(Integer id) throws BaseException;
	RoomResponse save(RoomRequest request) throws BaseException;
	Boolean deleteById(Integer id) throws BaseException;
	List<RoomResponse> findByIsActive() throws BaseException;
	
}
