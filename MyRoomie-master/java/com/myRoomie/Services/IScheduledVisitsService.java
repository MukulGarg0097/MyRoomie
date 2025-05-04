package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.ScheduledVisitsRequest;
import com.myRoomie.response.ScheduledVisitsResponse;

public interface IScheduledVisitsService{

	public ScheduledVisitsResponse save(ScheduledVisitsRequest request) throws BaseException;

	public List<ScheduledVisitsResponse> findAll() throws BaseException;

	public ScheduledVisitsResponse findById(Integer id) throws BaseException;

	public Boolean deleteById(Integer id) throws BaseException;
}
