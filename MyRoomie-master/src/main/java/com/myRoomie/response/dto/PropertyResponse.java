package com.myRoomie.response.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;
import com.myRoomie.response.PropertyFileResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PropertyResponse extends BaseCreatedPojo{

    private String idProductLocation;
	private String propertyName;
	private String aboutProperty;
	private String sharingType;
	private String roomType;
	private String propertyGenderType;
	private Boolean isFeatureFlag;
	private String propertyType;
	private String city;
	private Boolean isActive;
	private Double startAmount;
	private Double discPercentage;
	
	private Set<Integer> amenityIds;
	
	private Set<MasterRoomAmenitiesResponse> masterAmenities;	
	
	private Set<PropertyFileResponse> propertyImagesUrl;
	
	private Set<RoomResponse> rooms;
	
	private Set<NearByPlacesResponse> nearByPlaces;
	
    private AddressResponse address;
    
    private String view360;
	
}
