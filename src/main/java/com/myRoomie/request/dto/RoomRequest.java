package com.myRoomie.request.dto;

import java.util.Set;

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
	
	private Set<RoomVariantRequest> roomVariants;
	
	private Set<FileRequest> roomImagesUrl;
	
	private Set<RoomAmenitiesMapperRequest> roomAmenities;
	
	private Set<ChargesRequest> charges;
	
	
}
