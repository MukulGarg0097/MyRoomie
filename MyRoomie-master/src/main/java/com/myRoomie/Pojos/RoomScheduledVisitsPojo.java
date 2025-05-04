package com.myRoomie.Pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomScheduledVisitsPojo extends BaseCreatedPojo {

	private String roomTypeName;
	private Double startAmount;
	private Boolean availabilityStatus;
	private Integer noOfBeds;
	private Integer maxNoOfGuests;
	private String sharingStatus;
	private String[] services;
	private String[] equipments;
	private Integer propertyId;
	private Boolean isActive;

//	private Set<RoomAmenitiesMapperResponse> roomAmenitiesMapper;
//	
//	private Set<ChargesEntity> charges;
	
}
