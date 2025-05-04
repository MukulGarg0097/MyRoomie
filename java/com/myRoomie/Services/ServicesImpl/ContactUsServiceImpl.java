package com.myRoomie.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.ContactUsEntity;
import com.myRoomie.Repository.IContactUsRepository;
import com.myRoomie.Services.IContactUsService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.ContactUsMapper;
import com.myRoomie.request.ContactUsRequest;
import com.myRoomie.response.ContactUsResponse;

@Service
public class ContactUsServiceImpl implements IContactUsService{

	@Autowired
	IContactUsRepository contactUsRepo;

	@Override
	public Page<ContactUsResponse> findAll(Pageable pageable)  throws BaseException{
		Page<ContactUsEntity> paging = contactUsRepo.findAll(pageable);
		if(CollectionUtils.isEmpty(paging.getContent())) 
			return null;
		Page<ContactUsResponse> pagedResponse=new PageImpl<>(ContactUsMapper.mapContactUsEntityToResponse(paging.getContent()),pageable,paging.getTotalElements());
		return pagedResponse;
	}

	@Override
	public ContactUsResponse findById(Integer id)  throws BaseException{
		ContactUsEntity entity =new ContactUsEntity();
		if(contactUsRepo.existsById(id)) 
			entity = contactUsRepo.getOne(id);
		else
			return null;
		return ContactUsMapper.mapContactUsEntityToResponse(entity);
	}

	@Override
	public ContactUsResponse save(ContactUsRequest request)  throws BaseException{
		ContactUsEntity entity = contactUsRepo.save(ContactUsMapper.mapContactUsRequestToEntity(request));
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return ContactUsMapper.mapContactUsEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
//		contactUsRepo.deleteById(id);
			return true;
	}
}
