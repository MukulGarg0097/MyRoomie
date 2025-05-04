package com.myRoomie.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyParamRequest{

	String propertyGenderType;
	String city;
	Boolean isActive;
	Boolean isFeatureFlag;
	String propertyType;
	Double latitude;
	Double longitude;
	String propertyName;
}
