package com.myRoomie.Services;

import java.util.List;

import com.myRoomie.exceptions.BaseException;
import com.myRoomie.request.FileRequest;
import com.myRoomie.response.FileResponse;

public interface IFileService{

	List<FileResponse> findAll() throws BaseException;

	FileResponse findById(Integer id) throws BaseException;

	FileResponse save(FileRequest request) throws BaseException;

	Boolean deleteById(Integer id) throws BaseException;

}
