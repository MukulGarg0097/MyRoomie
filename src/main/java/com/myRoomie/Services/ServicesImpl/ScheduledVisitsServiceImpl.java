package com.myRoomie.Services.ServicesImpl;

import com.myRoomie.Entities.ScheduledVisitsEntity;
import com.myRoomie.Repository.IScheduledVisitsRepository;
import com.myRoomie.Services.IScheduledVisitsService;
import com.myRoomie.Services.eventListner.EventPojo;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.ScheduledVisitsMapper;
import com.myRoomie.request.dto.ScheduledVisitsRequest;
import com.myRoomie.response.dto.ScheduledVisitsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ScheduledVisitsServiceImpl implements IScheduledVisitsService{

	@Autowired
	IScheduledVisitsRepository scheduledVisitsRepo;
	
	@Autowired
	ScheduledVisitsMapper scheduledVisitsMapper;

	@Autowired
    private ApplicationEventPublisher eventPublisher;

	@Override
	public ScheduledVisitsResponse save(ScheduledVisitsRequest request)  throws BaseException
	{
		ScheduledVisitsEntity entity=scheduledVisitsMapper.mapScheduledVisitsRequestToEntity(request);
		if(ObjectUtils.isEmpty(entity)) 
			return null;
        sendEmail(entity);
		return scheduledVisitsMapper.mapScheduledVisitsEntityToResponse(scheduledVisitsRepo.save(entity));
	}

	@Override
	public List<ScheduledVisitsResponse> findAll(Pageable pageable)  throws BaseException{
		Pageable pageabled=PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("created").descending());
		Page<ScheduledVisitsEntity> entity= scheduledVisitsRepo.findAll(pageabled);
		if(CollectionUtils.isEmpty(entity.getContent())) 
			return null;
		return scheduledVisitsMapper.mapScheduledVisitsEntityToResponse(entity.getContent());
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

    private void sendEmail(ScheduledVisitsEntity entity) {
        EventPojo emailObject = new EventPojo(this);
        emailObject.setScheduledVisitsEntity(entity);
        emailObject.setType(EventPojo.EventType.SCHEDULED_VISIT);
        eventPublisher.publishEvent(emailObject);
    }
	
}
