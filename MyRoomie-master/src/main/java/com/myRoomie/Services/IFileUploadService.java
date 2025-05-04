package com.myRoomie.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myRoomie.exceptions.BaseException;

public interface IFileUploadService {
    List<String> getAll() throws BaseException;

    String delete(List<String> url) throws BaseException;

    String upload(MultipartFile aFile);
}
