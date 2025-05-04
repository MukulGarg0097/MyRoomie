package com.myRoomie.Pojos.dto;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestCallBackPojo  extends BaseCreatedPojo{

	private String name;
	private String mobileNo;
}
