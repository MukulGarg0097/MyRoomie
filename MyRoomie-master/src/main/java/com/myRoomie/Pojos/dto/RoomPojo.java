package com.myRoomie.Pojos.dto;

import java.util.Set;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomPojo extends BaseCreatedPojo {

	private String roomTypeName;
	private Double startAmount;
	private Boolean availabilityStatus=true;
	private Integer noOfBeds;
	private Integer maxNoOfGuests;
	private String sharingStatus;
	private String services;
	private String equipments;
	private Integer propertyId;
	private Boolean isActive=true;
	
	private Set<RoomVariantPojo> roomVariants;
	
	private Set<FilePojo> roomImagesUrl;
	
	private Set<RoomAmenitiesMapperPojo> roomAmenitiesMapper;
	
	private Set<ChargesPojo> charges;
	
}
