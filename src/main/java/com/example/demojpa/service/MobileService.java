package com.example.demojpa.service;

import com.example.demojpa.dto.MobileDTO;
import com.example.demojpa.entity.Mobile;
import com.example.demojpa.repository.CustomRepository;
import com.example.demojpa.repository.MobileRepository;
import com.example.demojpa.request.CreateMobileRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MobileService {

    private MobileRepository mobileRepository;

    private CustomRepository customRepository;

    @Autowired
    public MobileService(MobileRepository mobileRepository, CustomRepository customRepository) {
        this.mobileRepository = mobileRepository;
        this.customRepository = customRepository;
    }

    public List<Mobile> getListMobile(){
        return mobileRepository.findAll();
    }

    public List<Mobile> getListMobile( Sort.Direction direction,String... fieldSort){
        return mobileRepository.findAll(Sort.by(direction, fieldSort));
    }

    @Transactional
    public MobileDTO create(CreateMobileRequest request){
//        Mobile mobile = Mobile.builder()
//                .name(request.getName())
//                .description(request.getDescription())
//                .createdTime(new Timestamp(System.currentTimeMillis()))
//                .build();
        Mobile mobile = new Mobile();
        mobile.setName(request.getName());
        mobile.setDescription(request.getDescription());


        mobile = mobileRepository.saveAndFlush(mobile);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mobile, MobileDTO.class);
    }

    public List<Mobile> filterListMobile(Integer type, Integer active){
        return customRepository.filterMobileByTypeAndActive(type, active);

//        return mobileRepository.findAllByActiveEqualsAndTypeEquals(active, type);
    }
}
