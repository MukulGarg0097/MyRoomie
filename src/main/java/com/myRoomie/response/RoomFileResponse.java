package com.myRoomie.response;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomFileResponse extends BaseCreatedPojo{

	private String url;
	private String type;
	private String caption;
	private Integer roomId;
	
}
