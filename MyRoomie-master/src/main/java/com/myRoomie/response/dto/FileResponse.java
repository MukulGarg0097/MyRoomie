package com.myRoomie.response.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FileResponse extends BaseCreatedPojo{	

	private String url;
	private String type;
	private String caption;
	private Integer propertyId;
	private Integer roomId;
	
}
