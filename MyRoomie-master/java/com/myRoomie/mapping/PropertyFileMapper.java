package com.myRoomie.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.FileEntity;
import com.myRoomie.Utilities.DateUtil;
import com.myRoomie.request.FileRequest;
import com.myRoomie.response.PropertyFileResponse;

@Service
public class PropertyFileMapper {
	
	public PropertyFileMapper() {
	}
	
	public FileEntity mapFileRequestToEntity(FileRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		FileEntity response=new FileEntity();
		response.setCaption(request.getCaption());
		response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setId(request.getId());
		response.setType(request.getType());
		response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUrl(request.getUrl());
		response.setPropertyId(request.getPropertyId());
		return response;
	}
	
	public PropertyFileResponse mapFileEntityToResponse(FileEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		PropertyFileResponse response=new PropertyFileResponse();
		response.setCaption(entity.getCaption());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setId(entity.getId());
		response.setType(entity.getType());
		response.setUrl(entity.getUrl());
		response.setPropertyId(entity.getPropertyId());
			return response;
	}
	
	public List<FileEntity> mapFileRequestToEntity(List<FileRequest> requestList)
	{
		if(CollectionUtils.isEmpty(requestList))
		{
			return null;
		}
		List<FileEntity> entityList=new ArrayList<>();
		for(FileRequest request : requestList)
		{
			FileEntity response=new FileEntity();
			response = mapFileRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<PropertyFileResponse> mapFileEntityToResponse(List<FileEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<PropertyFileResponse> responseList=new ArrayList<>();
		for(FileEntity entity : entityList)
		{
			PropertyFileResponse response=new PropertyFileResponse();
			response = mapFileEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
	
	public Set<FileEntity> mapFileRequestToEntity(Set<FileRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		Set<FileEntity> entitySet=new HashSet<>();
		for(FileRequest request : requestSet)
		{
			FileEntity response=new FileEntity();
			response = mapFileRequestToEntity(request);
			entitySet.add(response);
		}	
		return entitySet;
	}

	public Set<PropertyFileResponse> mapFileEntityToResponse(Set<FileEntity> entitySet) {
		if(CollectionUtils.isEmpty(entitySet))
		{
			return null;
		}
		Set<PropertyFileResponse> responseSet=new HashSet<>();
		for(FileEntity entity : entitySet)
		{
			PropertyFileResponse response=new PropertyFileResponse();
			response = mapFileEntityToResponse(entity);
			responseSet.add(response);
		}
			return responseSet;
	}
}