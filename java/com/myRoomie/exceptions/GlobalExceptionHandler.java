package com.myRoomie.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.myRoomie.constants.HTTPCode;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.response.BaseResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends DefaultHandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseBody
	public <T> BaseResponse<T> handleJacksonException(HttpMessageNotReadableException me,
			HttpServletResponse response) {
		logger.error("HttpMessageNotReadableException ", me);
		if(me.getCause() instanceof JsonMappingException) {
			return new BaseResponse<T>(true, null, ResponseCode.INCORRECT_JSON_DATA);
		}
		return new BaseResponse<T>(true, null, ResponseCode.BAD_REQUEST);
	}

	@ExceptionHandler(value = BaseException.class)
	@ResponseBody
	public ResponseEntity<BaseResponse<?>> handleMyException(BaseException me, HttpServletResponse response) {
		BaseResponse<Object> baseResponse = new BaseResponse<>(me);
		baseResponse.serverMsg = me.getServerMessage();
		ResponseCode responseCode = me.getResponseCode();
		String message = null;
		HttpStatus httpStatus;
		if(responseCode == null) {
			message = "Error : BaseException ";
			httpStatus = HttpStatus.OK;
		} else {
			message = responseCode.getMessage();
			httpStatus =getHttpStatusFromResponseCode(responseCode);
		}
		logger.error(message, me);
		return new ResponseEntity<BaseResponse<?>>(baseResponse, httpStatus);
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity<BaseResponse<?>> handleAllException(Exception e, HttpServletResponse response) {
		logger.error("Global Exception", e);
		e.printStackTrace();
		BaseResponse<?> baseResponse = new BaseResponse<>(true, null, ResponseCode.ERROR, e.getMessage());
		return new ResponseEntity<BaseResponse<?>>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private HttpStatus getHttpStatusFromResponseCode(ResponseCode responseCode) {
		switch(responseCode.getCode()) {
		case HTTPCode.OK:
			return HttpStatus.BAD_REQUEST;
		case HTTPCode.NO_CONTENT:
			return HttpStatus.NO_CONTENT;
		case HTTPCode.BAD_REQUEST:
			return HttpStatus.BAD_REQUEST;
		case HTTPCode.UNAUTHORIZED:
			return HttpStatus.UNAUTHORIZED;
		case HTTPCode.FORBIDDEN:
			return HttpStatus.FORBIDDEN;
		case HTTPCode.METHOD_NOT_ALLOWED:
			return HttpStatus.METHOD_NOT_ALLOWED;
		case HTTPCode.CONFLICT:
			return HttpStatus.CONFLICT;
		case HTTPCode.UNSUPPORTED:
			return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
		case HTTPCode.INVALID:
			return HttpStatus.UNPROCESSABLE_ENTITY;
		case HTTPCode.SERVER_ERROR:
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return null;
	}

}
