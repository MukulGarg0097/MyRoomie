package com.myRoomie.request;

import java.util.Set;

import com.myRoomie.Entities.ChargesEntity;
import com.myRoomie.Entities.RoomAmenitiesMapper;
import com.myRoomie.Entities.RoomVariantEntity;
import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest extends BaseCreatedPojo{

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
	
	private Set<RoomVariantEntity> roomVariants;
	
	private Set<FileRequest> roomImagesUrl;
	
	private Set<RoomAmenitiesMapper> roomAmenities;
	
	private Set<ChargesEntity> charges;
	
	
}
