package com.myRoomie.Services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.ScheduledVisitsRequest;
import com.myRoomie.response.dto.ScheduledVisitsResponse;

public interface IScheduledVisitsService{

	public ScheduledVisitsResponse save(ScheduledVisitsRequest request) throws BaseException;

	public List<ScheduledVisitsResponse> findAll(Pageable pageable) throws BaseException;

	public ScheduledVisitsResponse findById(Integer id) throws BaseException;

	public Boolean deleteById(Integer id) throws BaseException;
}
