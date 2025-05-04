package com.myRoomie.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.ContactUsRequest;
import com.myRoomie.response.ContactUsResponse;

public interface IContactUsService{

	Page<ContactUsResponse> findAll(Pageable pageable) throws BaseException;

	ContactUsResponse findById(Integer id) throws BaseException;

	ContactUsResponse save(ContactUsRequest request) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;

}
