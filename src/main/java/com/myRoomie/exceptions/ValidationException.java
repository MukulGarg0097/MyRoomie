package com.myRoomie.exceptions;

import com.myRoomie.constants.ResponseCode;

@SuppressWarnings("serial")
public class ValidationException extends BaseException {

	public ValidationException(ResponseCode responseCode) {
		super(responseCode);
	}

}
