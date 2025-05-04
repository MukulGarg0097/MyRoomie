package com.myRoomie.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.myRoomie.constants.ResponseCode;
import com.myRoomie.response.BaseResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestExceptionHandler extends DefaultHandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(RequestExceptionHandler.class);

//	@ExceptionHandler(value = ServletRequestBindingException.class)
//	@ResponseBody
//	public <T> BaseResponse<T> handleServletRequestBindingException(
//			ServletRequestBindingException ex,
//			HttpServletRequest request) {
//		logger.error("ServletRequestBindingException ", ex);
//		if(request.getHeader(PathMappings.HeaderValues.TOKEN) == null) {
//			return new BaseResponse<>(true, null, ResponseCode.TOKEN_NOT_PRESENT);
//		}
//		return new BaseResponse<>(true, null, ResponseCode.ERROR);
//	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public BaseResponse<?> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex,
			HttpServletRequest request) {
		logger.error("HttpRequestMethodNotSupportedException ", ex);
		StringBuilder builder = new StringBuilder();
	    builder.append(ex.getMethod());
	    builder.append(" method is not supported for this request. Supported methods are ");
	    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		return new BaseResponse<String>(true, builder.toString(), ResponseCode.REQUEST_NOT_SUPPORTED);
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseBody
    public BaseResponse<?> handle(NoHandlerFoundException ex){
        String message = "HTTP " + ex.getHttpMethod() + " for " + ex.getRequestURL() + " is not supported.";
        return new BaseResponse<String>(true, message, ResponseCode.REQUEST_NOT_SUPPORTED);
    }
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public BaseResponse<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		String message = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		return new BaseResponse<String>(true, message, ResponseCode.METHOD_ARGUMENT_INVALID);
	}

}
