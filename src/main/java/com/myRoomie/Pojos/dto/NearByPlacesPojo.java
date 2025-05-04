package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NearByPlacesPojo  extends BaseCreatedPojo{

	private String placeName;
	private String displayTime;
	private String type;
	private Integer propertyId;
	private Boolean showOnWebsite;
	
}
