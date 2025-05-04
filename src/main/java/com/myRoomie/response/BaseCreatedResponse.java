package com.myRoomie.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCreatedResponse {

	private Integer id;
	
	private String created;
	
	private String updated;
}
