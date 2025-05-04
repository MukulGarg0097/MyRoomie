package com.myRoomie.Services.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.myRoomie.Entities.CouponCodeEntity;
import com.myRoomie.Repository.ICouponCodeRepository;
import com.myRoomie.Services.ICouponCodeService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.CouponCodeMapper;
import com.myRoomie.request.dto.CouponCodeRequest;
import com.myRoomie.response.dto.CouponCodeResponse;

@Service
public class CouponCodeServiceImpl implements ICouponCodeService{

	@Autowired
	ICouponCodeRepository couponCodeRepo;
	
	@Autowired
	CouponCodeMapper couponCodeMapper;

	@Override
	public List<CouponCodeResponse> findAll()  throws BaseException{
		List<CouponCodeEntity> entity = couponCodeRepo.findAll();
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return couponCodeMapper.mapCouponCodeEntityToResponse(entity);
	}
	
	@Override
	public List<CouponCodeResponse> findByIsActive()  throws BaseException{
		List<CouponCodeEntity> entity = couponCodeRepo.findByIsActive(true);
		if(CollectionUtils.isEmpty(entity)) 
			return null;
		return couponCodeMapper.mapCouponCodeEntityToResponse(entity);
	}

	@Override
	public CouponCodeResponse findByCouponCodeName(String couponCodeName)  throws BaseException{
		CouponCodeEntity entity = couponCodeRepo.findByCouponCodeName(couponCodeName);
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return couponCodeMapper.mapCouponCodeEntityToResponse(entity);
	}
	
	@Override
	public CouponCodeResponse findById(Integer id)  throws BaseException{
		CouponCodeEntity entity = new CouponCodeEntity();
		if(couponCodeRepo.existsById(id)) 
			entity = couponCodeRepo.findById(id).get();
		else
			return null;
		return couponCodeMapper.mapCouponCodeEntityToResponse(entity);
	}

	@Override
	public CouponCodeResponse save(CouponCodeRequest request)  throws BaseException{
		if(!ObjectUtils.isEmpty(findByCouponCodeName(request.getCouponCodeName())))
			return null;	
		CouponCodeEntity entity = couponCodeRepo.save(couponCodeMapper.mapCouponCodeRequestToEntity(request));
		if(ObjectUtils.isEmpty(entity)) 
			return null;
		return couponCodeMapper.mapCouponCodeEntityToResponse(entity);
	}

	@Override
	public Boolean deleteById(Integer id)  throws BaseException{
		CouponCodeEntity entity = new CouponCodeEntity();
		if(couponCodeRepo.existsById(id)) 
			entity = couponCodeRepo.findById(id).get();
		else
			return null;
		entity.setIsActive(false);
		couponCodeRepo.save(entity);
		return true;
	}
}
