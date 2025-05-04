package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilePojo extends BaseCreatedPojo{

	private String url;
	private String type;
	private String caption;
	private Integer propertyId;
	private Integer roomId;
}