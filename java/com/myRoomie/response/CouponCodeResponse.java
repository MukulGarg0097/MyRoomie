package com.myRoomie.response;

import com.myRoomie.Pojos.BaseCreatedPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponCodeResponse extends BaseCreatedPojo{

	private String couponCodeName;
	private Boolean isActive;
	private String expiryDate;
	private String startDate;
	private Integer discountPercentage;
	private Boolean isExpired;
}
