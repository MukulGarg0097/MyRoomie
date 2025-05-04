package com.myRoomie.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myRoomie.Services.IFileUploadService;
import com.myRoomie.constants.PathMappings;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.response.BaseResponse;

@RestController
@RequestMapping(value = PathMappings.File.BASE_MAPPING)
public class FileUploadController {

    @Autowired
    private IFileUploadService fileUploadService;

    @GetMapping(path = PathMappings.ALL)
    public BaseResponse<List<String>> findAll() throws BaseException {
        List<String> url = fileUploadService.getAll();
        if (CollectionUtils.isEmpty(url)) {
            return new BaseResponse<>(true, null, ResponseCode.BAD_REQUEST);
        }
        return new BaseResponse<>(false, url, ResponseCode.OK);
    }

    @PostMapping(path = PathMappings.File.SINGLE_UPLOAD)
    public BaseResponse<String> singleFile(@RequestParam(value = "file", required = true) MultipartFile aFile) throws BaseException {
        String urls = fileUploadService.upload(aFile);
        if (TextUtils.isEmpty(urls))
            return new BaseResponse<>(true, null, ResponseCode.BAD_REQUEST);
        return new BaseResponse<>(false, urls, ResponseCode.OK);
    }

    @PostMapping(path = PathMappings.File.MULTIPLE_UPLOAD)
    public BaseResponse<List<String>> multipleFiles(@RequestParam("files") MultipartFile[] files) throws BaseException {

        List<String> urls = Arrays.asList(files)
                .stream()
                .map(file -> fileUploadService.upload(file))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(urls))
            return new BaseResponse<>(true, null, ResponseCode.BAD_REQUEST);
        return new BaseResponse<>(false, urls, ResponseCode.OK);
    }

    @DeleteMapping(path = PathMappings.ALL)
    public BaseResponse<String> delete(@RequestBody Map<String, List<String>> images) throws BaseException {

    	String response;
        if (images.containsKey("images"))
            response = fileUploadService.delete(images.get("images"));
        else
            return new BaseResponse<>(true, null, ResponseCode.NO_DETAIL_PRESENT);
        if (response == null)
            return new BaseResponse<>(true, null, ResponseCode.BAD_REQUEST);
        return new BaseResponse<>(false, response, ResponseCode.OK);

    }
}
