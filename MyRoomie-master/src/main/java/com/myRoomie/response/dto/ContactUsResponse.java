package com.myRoomie.response.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUsResponse extends BaseCreatedPojo{
	
	private String email;
	private String name;
	private String message;
	private String phone;
	private String looking;
	
}
