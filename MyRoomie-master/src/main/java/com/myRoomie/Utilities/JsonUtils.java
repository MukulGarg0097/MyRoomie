package com.myRoomie.Utilities;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonUtils {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	public static<T> String convertToJSON(T data) {
		try {
			return objectMapper.writeValueAsString(data);
		} catch (Exception e) {
			logger.error("Error in parsing list to json", e);
		}
		return null;
	}
	
	public static<T> List<T> convertJsonToList(String json, Class<T> classT) {
		List<T> response = null;
		try {
			CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, classT);
			response = new ObjectMapper().readValue(json, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
		return response;
	}
	
	public static<T> T fromJson(String json, Class<T> clazz){
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static<T> T fromJson(String json, Object object){
		System.out.println("JSON : " + json);
		try {
			return objectMapper.readerForUpdating(object).readValue(json);
		} catch (Exception e) {
		}
		return null;
	}
	
	public static<T> T fromJsonIgnoreUnknown(String json, Class<T> clazz){
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T fromJson(String json, TypeReference<T> typeReference) {
		try {
			return objectMapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
