package com.myRoomie.Pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyScheduledVisitsPojo extends BaseCreatedPojo {

    private String idProductLocation;
	private String propertyName;
	private String aboutProperty;
	private String propertyGenderType;
	private Boolean isFeatureFlag;
	private String sharingType;
	private String roomType;
	private Boolean isActive;
	private String propertyType;
	private String city;
	private String view360;
//	private Double startAmount;
//	private Double discPercentage;
//	
//	private Set<Integer> amenityIds;
//	
//	private Set<MasterRoomAmenitiesResponse> masterAmenities;	
//	
//	
//	
//	private Set<NearByPlacesResponse> nearByPlaces;
//	private AddressEntity address;
}
