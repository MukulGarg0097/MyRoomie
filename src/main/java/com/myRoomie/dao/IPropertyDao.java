package com.myRoomie.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.PropertyParamRequest;

@Repository
public interface IPropertyDao{
	
	public List<PropertyEntity> findByParam(PropertyParamRequest request, String[] cols);
	
	public List<PropertyEntity> search(String search) throws BaseException;
}
