package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressPojo  extends BaseCreatedPojo{

	private String flatNo;
	private String houseNo;
	private String locality;
	private	String landmark;
	private Double latitude;
	private Double longitude;
	private String city;
	private String state;
	private String country;
	private String pincode;
}
