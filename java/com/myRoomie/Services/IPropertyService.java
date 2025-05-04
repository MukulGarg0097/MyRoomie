package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.PropertyRequest;
import com.myRoomie.response.PropertyResponse;

public interface IPropertyService {

	PropertyResponse save(PropertyRequest request) throws BaseException;

	List<PropertyResponse> findAll() throws BaseException;

	PropertyResponse findById(Integer id) throws BaseException;

	List<PropertyResponse> findByCity(String city) throws BaseException;

	List<PropertyResponse> findByGenderType(String propertyGenderType) throws BaseException;

	List<PropertyResponse> findByPropertyType(String propertyType) throws BaseException;

	List<PropertyResponse> findByPropertyTypeAndCity(String propertyType, String city) throws BaseException;

	List<PropertyResponse> findByPropertyGenderTypeAndCity(String propertyGenderType, String city) throws BaseException;

	List<PropertyResponse> findByLattitudeAndLongitude(Double lattitude, Double longitude) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;

	List<PropertyResponse> findByIsActive() throws BaseException;

	List<PropertyResponse> findAllByFeatured(Boolean isFeatureFlag) throws BaseException;

}
