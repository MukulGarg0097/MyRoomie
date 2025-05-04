package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomAmenitiesMapperPojo extends BaseCreatedPojo{

	private String amenityDescription;
	
	private Boolean	amenityMappingStatus=false;
	
	private Integer masterRoomAmenityId;
	private Integer roomId;
}