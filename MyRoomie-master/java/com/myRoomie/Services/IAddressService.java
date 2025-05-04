package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.AddressRequest;
import com.myRoomie.response.AddressResponse;

public interface IAddressService{

	List<AddressResponse> findAll() throws BaseException;

	AddressResponse findById(Integer id) throws BaseException;

	List<AddressResponse> findByCity(String city) throws BaseException;

	List<AddressResponse> findByState(String state) throws BaseException;

	List<AddressResponse> findByCountry(String country) throws BaseException;

	List<AddressResponse> findByPincode(String pincode) throws BaseException;

	List<AddressResponse> findByLattitudeAndLongitude(Double lattitude, Double longitude) throws BaseException;

	AddressResponse save(AddressRequest request) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;

}
