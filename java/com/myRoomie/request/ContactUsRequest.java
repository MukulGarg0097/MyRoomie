package com.myRoomie.request;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUsRequest extends BaseCreatedPojo{

	private String email;
	private String name;
	private String message;
	
}
