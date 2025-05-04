package com.myRoomie.Services.ServicesImpl;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.myRoomie.Services.IFileUploadService;
import com.myRoomie.constants.ResponseCode;
import com.myRoomie.exceptions.BaseException;

@SuppressWarnings("rawtypes")
@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Value("${com.cloudinary.cloud_name}")
    String mCloudName;

    @Value("${com.cloudinary.api_key}")
    String mApiKey;

    @Value("${com.cloudinary.api_secret}")
    String mApiSecret;

    @Value("${com.cloudinary.bucket_name}")
    String bucketName;

	@Override
    public List<String> getAll() throws BaseException {
        Cloudinary c = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);
        List<String> retval = new ArrayList<>();
        try {
            Map response = c.api().resource("", ObjectUtils.asMap("type", "upload"));
            JSONObject json = new JSONObject(response);
            JSONArray ja = json.getJSONArray("resources");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject j = ja.getJSONObject(i);
                retval.add(j.getString("url"));
            }
            return retval;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String delete(List<String> url) throws BaseException {
        if (CollectionUtils.isEmpty(url))
            throw new BaseException(ResponseCode.EMPTY_REQUEST);
        Cloudinary c = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);
        try {
            Map response = c.api().deleteResources(fileNameUrl(url), ObjectUtils.emptyMap());
            JSONObject json = new JSONObject(response);
            return json.getJSONObject("deleted").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String upload(MultipartFile aFile) {
        Cloudinary c = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);
        try {
            File f = Files.createTempFile("temp", aFile.getOriginalFilename()).toFile();
            aFile.transferTo(f);
            Map response = c.uploader().upload(f, ObjectUtils.asMap("folder", bucketName,
                    "public_id", getFileName(aFile.getOriginalFilename())));
            JSONObject json = new JSONObject(response);
            String url = json.getString("url");
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    private String getFileName(String filename) {
        return filename.lastIndexOf('.') > filename.lastIndexOf(File.separatorChar) ?
                filename.substring(0, filename.lastIndexOf('.'))
                : filename;
    }

//    private String randomFileName() {
//        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).concat("_image");
//
//    }

    private List<String> fileNameUrl(List<String> urls) {
        List<String> urlsNew = new ArrayList<>();
        for (String urlStr : urls) {
            String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);
            String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
//            String fileExtension = urlStr.substring(urlStr.lastIndexOf("."));
            urlsNew.add(bucketName.concat(fileNameWithoutExtension));
        }
        return urlsNew;
    }

}
