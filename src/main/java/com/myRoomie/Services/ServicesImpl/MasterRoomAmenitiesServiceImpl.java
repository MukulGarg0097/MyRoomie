package com.myRoomie.Services.ServicesImpl;

import com.myRoomie.Entities.MasterRoomAmenities;
import com.myRoomie.Repository.IMasterRoomAmenitiesRepository;
import com.myRoomie.Services.IMasterRoomAmenitiesService;
import com.myRoomie.exceptions.BaseException;
import com.myRoomie.mapping.MasterRoomAmenitiesMapper;
import com.myRoomie.request.dto.MasterRoomAmenitiesRequest;
import com.myRoomie.response.dto.MasterRoomAmenitiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.myRoomie.Utilities.FileUtil.secureUrl;

@Service
public class MasterRoomAmenitiesServiceImpl implements IMasterRoomAmenitiesService {

    @Autowired
    IMasterRoomAmenitiesRepository roomAmenityRepo;

    @Autowired
    MasterRoomAmenitiesMapper masterRoomAmenitiesMapper;

    @Override
    public MasterRoomAmenitiesResponse save(MasterRoomAmenitiesRequest request) throws BaseException {
        MasterRoomAmenities entity = roomAmenityRepo.save(masterRoomAmenitiesMapper.mapRoomAmenityRequestToEntity(request));
        if (ObjectUtils.isEmpty(entity))
            return null;
        return masterRoomAmenitiesMapper.mapMasterRoomAmenitiesToResponse(entity);
    }

    @Override
    public List<MasterRoomAmenitiesResponse> findAll() throws BaseException {
        List<MasterRoomAmenities> entity = roomAmenityRepo.findAll();
        if (CollectionUtils.isEmpty(entity))
            return null;
        return masterRoomAmenitiesMapper.mapMasterRoomAmenitiesToResponse(entity);
    }

    @Override
    public MasterRoomAmenitiesResponse findById(Integer id) throws BaseException {
        MasterRoomAmenities entity = new MasterRoomAmenities();
        if (roomAmenityRepo.existsById(id))
            entity = roomAmenityRepo.findById(id).get();
        else
            return null;
        return masterRoomAmenitiesMapper.mapMasterRoomAmenitiesToResponse(entity);
    }

    @Override
    public Boolean deleteById(Integer id) throws BaseException {
//		roomAmenityRepo.deleteById(id);
        return true;
    }

    @Override
    public Boolean fileSecure() {
        List<MasterRoomAmenities> entity = roomAmenityRepo.findAll();
        if (!CollectionUtils.isEmpty(entity)) {
            entity.forEach(d -> {
                if (d.getMasterAmenityIconUrl() != null) {
                    String url = secureUrl(d.getMasterAmenityIconUrl());
                    d.setMasterAmenityIconUrl(url);
                    roomAmenityRepo.save(d);
                }
            });
        }
        return true;
    }


}
