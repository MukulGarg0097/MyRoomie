package com.myRoomie.Services;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.dto.FileRequest;
import com.myRoomie.response.dto.FileResponse;

import java.util.List;

public interface IFileService{

	List<FileResponse> findAll() throws BaseException;

	FileResponse findById(Integer id) throws BaseException;

	FileResponse save(FileRequest request) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;
}
