package com.myRoomie.response;

import java.util.Set;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponse extends BaseCreatedPojo{

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
	
	private Set<RoomVariantResponse> roomVariants;
	
	private Set<RoomFileResponse> roomImagesUrl;
	
	private Set<RoomAmenitiesMapperResponse> roomAmenities;
	
	private Set<ChargesResponse> charges;
}
