package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterRoomAmenitiesPojo extends BaseCreatedPojo{

	private String masterAmenityName;
	private String masterAmenityDescription;
	private String masterAmenityIconUrl;
}
