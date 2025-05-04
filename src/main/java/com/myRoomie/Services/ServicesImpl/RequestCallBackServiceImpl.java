package com.myRoomie.Services.ServicesImpl;

import com.myRoomie.Entities.RequestCallBackEntity;
import com.myRoomie.Repository.IRequestCallBackRepository;
import com.myRoomie.Services.IRequestCallBackService;
import com.myRoomie.Services.eventListner.EventPojo;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.RequestCallBackMapper;
import com.myRoomie.request.dto.RequestCallBackRequest;
import com.myRoomie.response.dto.RequestCallBackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class RequestCallBackServiceImpl implements IRequestCallBackService {

	@Autowired
	IRequestCallBackRepository callBackRepo;
	@Autowired
    private ApplicationEventPublisher eventPublisher;

	@Override
	public List<RequestCallBackResponse> findAll() throws BaseException {
		List<RequestCallBackEntity> entity = callBackRepo.findAll();
		if (CollectionUtils.isEmpty(entity))
			return null;
		return RequestCallBackMapper.mapCallBackEntityToResponse(entity);
	}

	@Override
	public RequestCallBackResponse findById(Integer id) throws BaseException {
		RequestCallBackEntity entity = new RequestCallBackEntity();
		if (callBackRepo.existsById(id))
			entity = callBackRepo.getOne(id);
		else
			return null;
		return RequestCallBackMapper.mapCallBackEntityToResponse(entity);
	}

	@Override
	public RequestCallBackResponse save(RequestCallBackRequest request) throws BaseException {
		RequestCallBackEntity entity = callBackRepo.save(RequestCallBackMapper.mapCallBackRequestToEntity(request));
		if (ObjectUtils.isEmpty(entity))
			return null;
        sendEmail(entity);
		return RequestCallBackMapper.mapCallBackEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id) throws BaseException {
		if (callBackRepo.existsById(id))
			callBackRepo.deleteById(id);
		return true;
	}

    private void sendEmail(RequestCallBackEntity entity) {
        EventPojo emailObject = new EventPojo(this);
        emailObject.setCallBackEntity(entity);
        emailObject.setType(EventPojo.EventType.REQUEST_CALLBACK);
        eventPublisher.publishEvent(emailObject);
    }

}
