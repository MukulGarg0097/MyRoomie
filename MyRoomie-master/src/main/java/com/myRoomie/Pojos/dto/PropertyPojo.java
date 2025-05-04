package com.myRoomie.Pojos.dto;

import java.util.Set;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyPojo extends BaseCreatedPojo {

	private String propertyName;
	private String aboutProperty;
	private String sharingType;
	private String roomType;
	private String propertyGenderType;
	private Boolean isFeatureFlag;
	private Boolean isActive;
	private String propertyType;
	private String city;
	private Double startAmount;
	private Double discPercentage;
	private String amenityIds;
	private String view360;
	
	private Set<FilePojo> propertyImagesUrl;
	
	private Set<RoomPojo> rooms;
	
	private Set<NearByPlacesPojo> nearByPlaces;
	
    private AddressPojo address;
}
