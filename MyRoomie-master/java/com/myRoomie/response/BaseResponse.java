package com.myRoomie.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;

import lombok.NoArgsConstructor;

@JsonInclude(value = Include.NON_NULL)
@NoArgsConstructor
public class BaseResponse<T> {
	public boolean error;
	public String statusCode;
	public String message;
	public String serverMsg;
	public T data;

	public BaseResponse(boolean error, String statusCode, String message, T data) {
		this.error = error;
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public BaseResponse(BaseException baseException) {
		this.error = true;
		this.data = null;
		if (baseException.getResponseCode() != null) {
			this.statusCode = baseException.getResponseCode().getCode();
			this.message = baseException.getResponseCode().getMessage();
		} else {
			this.message = baseException.getExceptionMessage();
		}
	}

	public BaseResponse(boolean isError, T data, ResponseCode responseCode) {
		this.error = isError;
		this.data = data;
		this.statusCode = responseCode.getCode();
		this.message = responseCode.getMessage();
	}

	public BaseResponse(boolean isError, T data, ResponseCode responseCode, String serverMsg) {
		this.error = isError;
		this.data = data;
		this.statusCode = responseCode.getCode();
		this.message = responseCode.getMessage();
		this.serverMsg = serverMsg;
	}

	public static BaseResponse<?> okNullResponse() {
		return okResponse(null);
	}

	public static <E> BaseResponse<E> okNullResponse(ResponseCode responseCode) {
		return get(false, null, responseCode);
	}

	public static <E> BaseResponse<E> okResponse(E data) {
		return get(false, data, ResponseCode.OK);
	}

	public static <E> BaseResponse<E> get(boolean isError, E data, ResponseCode responseCode) {
		return new BaseResponse<E>(isError, data, responseCode);
	}

}
