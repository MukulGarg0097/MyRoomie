package com.myRoomie.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCreatedRequest {

	private Integer id;
	
	private String created;
	
	private String updated;
}
