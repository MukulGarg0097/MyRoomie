package com.myRoomie.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myRoomie.Services.IPropertyService;
import com.myRoomie.Services.ISearchService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.SearchRequest;
import com.myRoomie.response.BaseResponse;
import com.myRoomie.response.PropertyResponse;
import com.myRoomie.response.SearchResponse;

@RestController
@RequestMapping(path=PathMappings.Search.BASE_MAPPING)
public class SearchController {

	@Autowired
	IPropertyService propertyService;
	
	@Autowired
	ISearchService searchService;
//	, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
	
	@RequestMapping(path=PathMappings.Search.SEARCH, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<SearchResponse>> searchByString(
			@RequestParam(required=true , value = "search") String search,
			@RequestParam(required=false , value = "latitude") Double latitude,
			@RequestParam(required=false , value = "longitude") Double longitude)
					throws BaseException{
		if(search==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.INVALID_SEARCH_FIELD);
		}
		List<SearchResponse> response = searchService.Search(search,latitude,longitude);
		if(ObjectUtils.isEmpty(response)) {
			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
		}
		return new BaseResponse<List<SearchResponse>>(false, response, ResponseCode.OK);
	}
	
	@RequestMapping(path=PathMappings.Search.GET_PROPERTY, method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse<List<PropertyResponse>> findProperty(
			@RequestBody(required=false) SearchRequest request)
			throws BaseException{
		if(request==null)
		{
			return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
		}
		List<PropertyResponse> response =new ArrayList<>();
		if(request.getId()!=null){
			response.add(propertyService.findById(request.getId()));
			if(ObjectUtils.isEmpty(response)) {
				return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
		}}
		else{
			response = searchService.getPropertiesNearBy(request);
			if(ObjectUtils.isEmpty(response)) {
				return new BaseResponse<>(false, null, ResponseCode.OK);
			}}
		return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
	}
}
