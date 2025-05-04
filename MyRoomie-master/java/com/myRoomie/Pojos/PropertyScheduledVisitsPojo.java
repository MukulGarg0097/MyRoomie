package com.myRoomie.Pojos;

import com.myRoomie.response.AddressResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyScheduledVisitsPojo extends BaseCreatedPojo {

	private String propertyName;
	private String aboutProperty;
	private String propertyGenderType;
	private Boolean isFeatureFlag;
	private Boolean isActive;
	private String propertyType;
	private String city;
	
	private AddressResponse address;
}
