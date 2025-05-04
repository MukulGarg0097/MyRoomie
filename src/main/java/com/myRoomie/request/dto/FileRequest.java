package com.myRoomie.request.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FileRequest extends BaseCreatedPojo{

	private String url;
	private String type;
	private String caption;
	private Integer propertyId;
	private Integer roomId;
	
}
