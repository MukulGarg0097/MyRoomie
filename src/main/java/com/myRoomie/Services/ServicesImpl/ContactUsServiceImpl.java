package com.myRoomie.Services.ServicesImpl;

import com.myRoomie.Entities.ContactUsEntity;
import com.myRoomie.Repository.IContactUsRepository;
import com.myRoomie.Services.IContactUsService;
import com.myRoomie.Services.eventListner.EventPojo;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.ContactUsMapper;
import com.myRoomie.request.dto.ContactUsRequest;
import com.myRoomie.response.dto.ContactUsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Service
public class ContactUsServiceImpl implements IContactUsService {

	@Autowired
	IContactUsRepository contactUsRepo;
	@Autowired
    private ApplicationEventPublisher eventPublisher;

	@Override
	public Page<ContactUsResponse> findAll(Pageable pageable) throws BaseException {
		Page<ContactUsEntity> paging = contactUsRepo.findAll(pageable);
		if (CollectionUtils.isEmpty(paging.getContent()))
			return null;
		Page<ContactUsResponse> pagedResponse = new PageImpl<>(
				ContactUsMapper.mapContactUsEntityToResponse(paging.getContent()), pageable, paging.getTotalElements());
		return pagedResponse;
	}

	@Override
	public ContactUsResponse findById(Integer id) throws BaseException {
		ContactUsEntity entity = new ContactUsEntity();
		if (contactUsRepo.existsById(id))
			entity = contactUsRepo.getOne(id);
		else
			return null;
		return ContactUsMapper.mapContactUsEntityToResponse(entity);
	}

	@Override
	public ContactUsResponse save(ContactUsRequest request) throws BaseException {
		ContactUsEntity entity = contactUsRepo.save(ContactUsMapper.mapContactUsRequestToEntity(request));
		if (ObjectUtils.isEmpty(entity))
			return null;
        sendEmail(entity);
		return ContactUsMapper.mapContactUsEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id) throws BaseException {
//		contactUsRepo.deleteById(id);
		return true;
	}

    private void sendEmail(ContactUsEntity entity) {
        EventPojo emailObject = new EventPojo(this);
        emailObject.setContactUsEntity(entity);
        emailObject.setType(EventPojo.EventType.CONTACT_US);
        eventPublisher.publishEvent(emailObject);
    }
}
