package com.myRoomie.response;

import java.util.Set;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyResponse extends BaseCreatedPojo{

	private String propertyName;
	private String aboutProperty;
	private String propertyGenderType;
	private Boolean isFeatureFlag;
	private String propertyType;
	private String city;
	private Boolean isActive;
	
	private Set<PropertyFileResponse> propertyImagesUrl;
	
	private Set<RoomResponse> rooms;
	
    private AddressResponse address;
	
}
