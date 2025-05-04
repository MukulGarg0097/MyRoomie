package com.myRoomie.Controller;

import com.myRoomie.Services.IPropertyService;
import com.myRoomie.Services.ISearchService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.SearchRequest;
import com.myRoomie.response.BaseResponse;
import com.myRoomie.response.SearchResponse;
import com.myRoomie.response.dto.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = PathMappings.Search.BASE_MAPPING)
public class SearchController {

    @Autowired
    IPropertyService propertyService;

    @Autowired
    ISearchService searchService;

    @RequestMapping(path = PathMappings.Search.SEARCH, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<SearchResponse>> searchByString(
            @RequestParam(required = true, value = "search") String search,
            @RequestParam(required = false, value = "latitude") Double latitude,
            @RequestParam(required = false, value = "longitude") Double longitude)
            throws BaseException {
        if (search == null) {
            return new BaseResponse<>(true, null, ResponseCode.INVALID_SEARCH_FIELD);
        }
        List<SearchResponse> response = searchService.Search(search, latitude, longitude);
        if (ObjectUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<List<SearchResponse>>(false, response, ResponseCode.OK);
    }

//	@RequestMapping(path=PathMappings.Search.SEARCH, method = RequestMethod.GET)
//	@ResponseBody
//	public BaseResponse<?> searchByString(
//			@RequestParam(required=true , value = "search") String search,
//			@RequestParam(required=false , value = "latitude") Double latitude,
//			@RequestParam(required=false , value = "longitude") Double longitude)
//					throws BaseException, IOException{
//		if(search==null)
//		{
//			return new BaseResponse<>(true, null, ResponseCode.INVALID_SEARCH_FIELD);
//		}
//		List<SearchResponse> response = searchService.delete(search);
//		
//		if(ObjectUtils.isEmpty(response)) {
//			return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
//		}
//		return new BaseResponse<>(false, (response), ResponseCode.OK);
//	}

    @RequestMapping(path = PathMappings.Search.GET_PROPERTY, method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<?> findProperty(
            @RequestBody(required = true) SearchRequest request)
            throws BaseException {
        if (ObjectUtils.isEmpty(request)) {
            return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
        }
        List<PropertyResponse> response = new ArrayList<>();
        if (request.getId() != null) {
            PropertyResponse resp = propertyService.findById(request.getId(), request.idProductLocation);
            if (ObjectUtils.isEmpty(resp)) {
                return new BaseResponse<>(true, null, ResponseCode.INVALID_ID);
            }
            return new BaseResponse<PropertyResponse>(false, resp, ResponseCode.OK);
        } else {
            response = searchService.getPropertiesNearBy(request);
            if (CollectionUtils.isEmpty(response)) {
                return new BaseResponse<>(true, null, ResponseCode.NO_PROPERTY_FOUND);
            }
        }
        return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
    }
}
