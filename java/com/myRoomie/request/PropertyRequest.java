package com.myRoomie.request;

import java.util.Set;

import com.myRoomie.Entities.AddressEntity;
import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyRequest extends BaseCreatedPojo{

	private String propertyName;
	private String aboutProperty;
	private String propertyGenderType;
	private Boolean isFeatureFlag;
	private String propertyType;
	private String city;
	private Boolean isActive;
	
	private Set<FileRequest> propertyImagesUrl;
	
	private Set<RoomRequest> rooms;
	
    private AddressEntity address;
	
}
