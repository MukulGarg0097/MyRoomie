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
import com.myRoomie.response.RoomFileResponse;

@Service
public class RoomFileMapper {
	
	public RoomFileMapper() {
	}
	
	public FileEntity mapFileRequestToEntity(FileRequest request)
	{
		if(ObjectUtils.isEmpty(request))
		{
			return null;
		}
		FileEntity response=new FileEntity();
		response.setId(request.getId());
		response.setCreated(DateUtil.parseDate(request.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.parseDate(request.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setCaption(request.getCaption());
		response.setType(request.getType());
		response.setUrl(request.getUrl());
		response.setRoomId(request.getRoomId());
		return response;
	}
	
	public RoomFileResponse mapFileEntityToResponse(FileEntity entity)
	{
		if(ObjectUtils.isEmpty(entity))
		{
			return null;
		}
		RoomFileResponse response=new RoomFileResponse();
		response.setCaption(entity.getCaption());
		response.setCreated(DateUtil.formateDate(entity.getCreated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setUpdated(DateUtil.formateDate(entity.getUpdated(), DateUtil.DB_TIMESTAMP_PATTERN));
		response.setId(entity.getId());
		response.setType(entity.getType());
		response.setUrl(entity.getUrl());
		response.setRoomId(entity.getRoomId());
			return response;
	}
	
	public List<FileEntity> mapFileRequestToEntity(List<FileRequest> requestSet)
	{
		if(CollectionUtils.isEmpty(requestSet))
		{
			return null;
		}
		List<FileEntity> entityList=new ArrayList<>();
		for(FileRequest request : requestSet)
		{
			FileEntity response=new FileEntity();
			response = mapFileRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public List<RoomFileResponse> mapFileEntityToResponse(List<FileEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		List<RoomFileResponse> responseList=new ArrayList<>();
		for(FileEntity entity : entityList)
		{
			RoomFileResponse response=new RoomFileResponse();
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
		Set<FileEntity> entityList=new HashSet<>();
		for(FileRequest request : requestSet)
		{
			FileEntity response=new FileEntity();
			response = mapFileRequestToEntity(request);
			entityList.add(response);
		}	
		return entityList;
	}

	public Set<RoomFileResponse> mapFileEntityToResponse(Set<FileEntity> entityList) {
		if(CollectionUtils.isEmpty(entityList))
		{
			return null;
		}
		Set<RoomFileResponse> responseList=new HashSet<>();
		for(FileEntity entity : entityList)
		{
			RoomFileResponse response=new RoomFileResponse();
			response = mapFileEntityToResponse(entity);
			responseList.add(response);
		}
			return responseList;
	}
}