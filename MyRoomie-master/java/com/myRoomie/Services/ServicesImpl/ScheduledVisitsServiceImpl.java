package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.ScheduledVisitsEntity;
import com.myRoomie.Repository.IScheduledVisitsRepository;
import com.myRoomie.Services.IScheduledVisitsService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.ScheduledVisitsMapper;
import com.myRoomie.request.ScheduledVisitsRequest;
import com.myRoomie.response.ScheduledVisitsResponse;

@Service
public class ScheduledVisitsServiceImpl implements IScheduledVisitsService{

	@Autowired
	IScheduledVisitsRepository scheduledVisitsRepo;
	
	@Autowired
	ScheduledVisitsMapper scheduledVisitsMapper;

	@Override
	public ScheduledVisitsResponse save(ScheduledVisitsRequest request)  throws BaseException
	{
		ScheduledVisitsEntity entity=scheduledVisitsMapper.mapScheduledVisitsRequestToEntity(request);
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return scheduledVisitsMapper.mapScheduledVisitsEntityToResponse(scheduledVisitsRepo.save(entity));
	}

	@Override
	public List<ScheduledVisitsResponse> findAll()  throws BaseException{
		List<ScheduledVisitsEntity> entity= scheduledVisitsRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return scheduledVisitsMapper.mapScheduledVisitsEntityToResponse(entity);
	}

	@Override
	public ScheduledVisitsResponse findById(Integer id)  throws BaseException{
		ScheduledVisitsEntity entity = new ScheduledVisitsEntity();
		if(scheduledVisitsRepo.existsById(id))
			entity = scheduledVisitsRepo.findById(id).get();
		else
			return null;
		return scheduledVisitsMapper.mapScheduledVisitsEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
//		scheduledVisitsRepo.deleteById(id);
		return true;
	}
	
	
}
