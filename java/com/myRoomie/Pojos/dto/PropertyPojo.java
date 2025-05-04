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
	private String propertyGenderType;
	private Boolean isFeatureFlag=false;
	private Boolean isActive=true;
	private String propertyType;
	private String city;
	
	private Set<FilePojo> propertyImagesUrl;
	
	private Set<RoomPojo> rooms;
	
    private AddressPojo address;
}
