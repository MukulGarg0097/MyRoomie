package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.FileEntity;
import com.myRoomie.Repository.IFileRepository;
import com.myRoomie.Services.IFileService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.FileMapper;
import com.myRoomie.request.FileRequest;
import com.myRoomie.response.FileResponse;

@Service
public class FileServiceImpl implements IFileService{

	@Autowired
	IFileRepository fileRepo;

	@Autowired
	FileMapper fileMapper;
	
	@Override
	public List<FileResponse> findAll()  throws BaseException{
		List<FileEntity> entity = fileRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return fileMapper.mapFileEntityToResponse(entity);
	}

	@Override
	public FileResponse findById(Integer id)  throws BaseException{
		FileEntity entity = new FileEntity();
		if(fileRepo.existsById(id))
			entity = fileRepo.findById(id).get();
		else
			return null;
		return fileMapper.mapFileEntityToResponse(entity);
	}

	@Override
	public FileResponse save(FileRequest request)  throws BaseException{
		FileEntity entity = fileMapper.mapFileRequestToEntity(request);
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return fileMapper.mapFileEntityToResponse(fileRepo.save(entity));
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
//		fileRepo.deleteById(id);
		return true;
	}

}
