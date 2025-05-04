package com.myRoomie.Controller;

import com.myRoomie.Services.IPropertyService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.PropertyMapper;
import com.myRoomie.request.PropertyParamRequest;
import com.myRoomie.request.dto.PropertyRequest;
import com.myRoomie.response.BaseResponse;
import com.myRoomie.response.dto.PropertyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = PathMappings.Property.BASE_MAPPING)
public class PropertyController {


    @Autowired
    IPropertyService propertyService;

    @Autowired
    PropertyMapper propMapper;

    @RequestMapping(path = PathMappings.Property.BASE_MAPPING_ALL, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> findAll()
            throws BaseException {
        List<PropertyResponse> responseList = propertyService.findAll();
        return new BaseResponse<>(false, responseList, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.Property.BASE_MAPPING_ALL_ACTIVE, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> findByIsActive()
            throws BaseException {
        List<PropertyResponse> responseList = propertyService.findByIsActive();
        return new BaseResponse<List<PropertyResponse>>(false, responseList, ResponseCode.OK);
    }

    @GetMapping(value = {PathMappings.ID_PARAM, PathMappings.ID_PARAM + PathMappings.ID_PRODUCT_NAME_PARAM})
    @ResponseBody
    public BaseResponse<PropertyResponse> findById(@PathVariable(value = "id") Integer id,
                                                   @PathVariable(value = "idProductLocation") Optional<String> idProductLocation) throws BaseException {
        PropertyResponse response = propertyService.findById(id, idProductLocation.orElse(""));
        if (ObjectUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<PropertyResponse>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.Property.CITY, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> findByCity(
            @RequestParam(required = true, value = "city") String city)
            throws BaseException {
        if (city == null) {
            return new BaseResponse<>(true, null, ResponseCode.CITY_NOT_PRESENT);
        }
        List<PropertyResponse> response = propertyService.findByCity(city);
        if (CollectionUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.Property.FEATURED, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> findAllByFeatured(
            @RequestParam(required = true, value = "isFeatureFlag") Boolean isFeatureFlag)
            throws BaseException {
        if (isFeatureFlag == null) {
            return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
        }
        List<PropertyResponse> response = propertyService.findAllByFeatured(isFeatureFlag);
        if (CollectionUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.Property.PROPERTY_GENDER_TYPE, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> findByGenderType(
            @RequestParam(required = true, value = "propertyGenderType") String propertyGenderType)
            throws BaseException {
        if (propertyGenderType == null) {
            return new BaseResponse<>(true, null, ResponseCode.PROPERTY_GENDER_TYPE);
        }
        List<PropertyResponse> response = propertyService.findByGenderType(propertyGenderType);
        if (CollectionUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.Property.PROPERTY_TYPE, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> findByPropertyType(
            @RequestParam(required = true, value = "propertyType") String propertyType)
            throws BaseException {
        if (propertyType == null) {
            return new BaseResponse<>(true, null, ResponseCode.PROPERTY_TYPE);
        }
        List<PropertyResponse> response = propertyService.findByPropertyType(propertyType);
        if (CollectionUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.Property.PROPERTY_TYPE_AND_CITY, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> findByPropertyTypeAndCity(
            @RequestParam(required = true, value = "propertyType") String propertyType,
            @RequestParam(required = true, value = "city") String city)
            throws BaseException {
        if (propertyType == null || city == null) {
            return new BaseResponse<>(true, null, ResponseCode.INCORRECT_JSON_DATA);
        }
        List<PropertyResponse> response = propertyService.findByPropertyTypeAndCity(propertyType, city);
        if (CollectionUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.Property.PROPERTY_GENDER_TYPE_AND_CITY, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> findByPropertyGenderTypeAndCity(
            @RequestParam(required = true, value = "propertyGenderType") String propertyGenderType,
            @RequestParam(required = true, value = "city") String city)
            throws BaseException {
        if (propertyGenderType == null || city == null) {
            return new BaseResponse<>(true, null, ResponseCode.INCORRECT_JSON_DATA);
        }
        List<PropertyResponse> response = propertyService.findByPropertyGenderTypeAndCity(propertyGenderType, city);
        if (CollectionUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.Property.LAT_LONG, method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> findByLattitudeAndLongitude(
            @RequestParam(required = true, value = "lattitude") Double lattitude,
            @RequestParam(required = true, value = "longitude") Double longitude)
            throws BaseException {
        if (lattitude == null || longitude == null) {
            return new BaseResponse<>(true, null, ResponseCode.BAD_REQUEST);
        }
        List<PropertyResponse> response = propertyService.findByLattitudeAndLongitude(lattitude, longitude);
        if (CollectionUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.Property.FIND, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponse<List<PropertyResponse>> find(
            @RequestBody(required = false) PropertyParamRequest request)
            throws BaseException {
        if (request == null) {
            return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
        }
        List<PropertyResponse> response = propertyService.find(request);
        if (CollectionUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.NO_DATA_PRESENT);
        }
        return new BaseResponse<List<PropertyResponse>>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.SAVE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponse<PropertyResponse> save(@Valid @RequestBody PropertyRequest request) throws BaseException {
        if (request == null) {
            return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
        }
        PropertyResponse response = propertyService.save(request);
        if (ObjectUtils.isEmpty(response)) {
            return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_SAVING);
        }
        return new BaseResponse<>(false, response, ResponseCode.OK);
    }

    @RequestMapping(path = PathMappings.UPDATE, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponse<PropertyResponse> update(@RequestBody PropertyRequest request) throws BaseException {
        if (request == null)
            return new BaseResponse<>(true, null, ResponseCode.EMPTY_REQUEST);
        PropertyResponse response = propertyService.update(request);
        if (ObjectUtils.isEmpty(response))
            return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_UPDATING);
        return new BaseResponse<>(false, response, ResponseCode.OK);
    }

    @DeleteMapping(value = {PathMappings.ID_PARAM, PathMappings.ID_PARAM + PathMappings.ID_PRODUCT_NAME_PARAM})
    @ResponseBody
    public BaseResponse<?> deleteById(@PathVariable(value = "id") Integer id, @PathVariable(value = "idProductLocation") Optional<String> idProductLocation,
                                      @RequestParam(required = false, value = "delete", defaultValue = "false") Boolean delete) throws BaseException {
        if (propertyService.deleteById(id, idProductLocation.orElse(""), delete))
            return new BaseResponse<>(false, null, ResponseCode.OK);
        return new BaseResponse<>(true, null, ResponseCode.ERROR_IN_DELETING);
    }

    // dummy api only for id update
    @RequestMapping(path = "updateIdProperty", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<?> updateIdProperty() {
        propertyService.updateId();
        return new BaseResponse<>(false, null, ResponseCode.OK);
    }


}


//Pagination


//@RequestMapping(path=PathMappings.Property.BASE_MAPPING_ALL, method = RequestMethod.GET)
//@ResponseBody
//public BaseResponse<Page<PropertyResponse>> findAll(Pageable pageable)
//		throws BaseException{
//	Page<PropertyEntity> paging=propertyRepository.findAll(pageable);
//	 Page<PropertyResponse> pagedResponse=new PageImpl<>(propMapper.mapPropertyEntityToResponse(paging.getContent()),pageable,paging.getTotalElements());
//	return new BaseResponse<Page<PropertyResponse>>(false, pagedResponse, ResponseCode.OK);
//}