package com.myRoomie.exceptions;

import com.myRoomie.constants.ResponseCode;

@SuppressWarnings("serial")
public class BaseException extends Exception {
	private String exceptionMessage;
	private ResponseCode responseCode;
	private String serverMessage;

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public String getServerMessage() {
		return serverMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public BaseException(String exceptionMessage) {
		super();
		this.exceptionMessage = exceptionMessage;
	}

	public BaseException(ResponseCode responseCode) {
		super();
		this.responseCode = responseCode;
	}

	public BaseException(ResponseCode responseCode, String serverMessage) {
		super();
		this.responseCode = responseCode;
		this.serverMessage = serverMessage;
	}

}
