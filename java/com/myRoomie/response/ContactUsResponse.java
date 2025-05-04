package com.myRoomie.response;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUsResponse extends BaseCreatedPojo{
	
	private String email;
	private String name;
	private String message;
	
}
