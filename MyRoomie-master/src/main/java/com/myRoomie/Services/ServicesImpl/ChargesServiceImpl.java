package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.ChargesEntity;
import com.myRoomie.Repository.IChargesRepository;
import com.myRoomie.Services.IChargesService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.ChargesMapper;
import com.myRoomie.request.dto.ChargesRequest;
import com.myRoomie.response.dto.ChargesResponse;

@Service
public class ChargesServiceImpl implements IChargesService{

	@Autowired
	IChargesRepository chargesRepo;
	
	@Autowired
	ChargesMapper chargesMapper;

	@Override
	public ChargesResponse save(ChargesRequest request)  throws BaseException
	{
		ChargesEntity entity=chargesMapper.mapChargesRequestToEntity(request);
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return chargesMapper.mapChargesEntityToResponse(chargesRepo.save(entity));
	}

	@Override
	public List<ChargesResponse> findAll()  throws BaseException{
		List<ChargesEntity> entity= chargesRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return chargesMapper.mapChargesEntityToResponse(entity);
	}

	@Override
	public ChargesResponse findById(Integer id)  throws BaseException{
		ChargesEntity entity=new ChargesEntity();
		if(chargesRepo.existsById(id)) 
			entity = chargesRepo.findById(id).get();
		else
			return null;
		return chargesMapper.mapChargesEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
//		chargesRepo.deleteById(id);
		return true;
	}
	
	
}
