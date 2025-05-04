package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ContactUsPojo  extends BaseCreatedPojo{

	private String email;
	private String name;
	private String message;
	private Integer da;
}
