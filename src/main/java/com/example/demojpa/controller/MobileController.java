package com.example.demojpa.controller;

import com.example.demojpa.constant.DateTimeConstant;
import com.example.demojpa.dto.MobileDTO;
import com.example.demojpa.entity.Mobile;
import com.example.demojpa.request.CreateMobileRequest;
import com.example.demojpa.request.FilterMobileRequest;
import com.example.demojpa.response.BaseItemResponse;
import com.example.demojpa.response.BaseListItemResponse;
import com.example.demojpa.response.BaseResponse;
import com.example.demojpa.service.MobileService;
import com.example.demojpa.utils.MyUtils;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mobile")
public class MobileController {
    private MobileService mobileService;

    @Autowired
    public MobileController(MobileService mobileService) {
        this.mobileService = mobileService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<MobileDTO>> getAllMobile() {
        ModelMapper modelMapper = new ModelMapper();
        List<MobileDTO> mobileDTOS = mobileService.getListMobile().stream().map(
                mobile -> modelMapper.map(mobile, MobileDTO.class)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(mobileDTOS);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MobileDTO>> filterMobile(@RequestParam(value = "type", required = false) Integer type,
                                                        @RequestParam(value = "active", required = false) Integer active) {
        ModelMapper modelMapper = new ModelMapper();
        List<MobileDTO> mobileDTOS = mobileService.filterListMobile(type, active).stream().map(
                mobile -> modelMapper.map(mobile, MobileDTO.class)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(mobileDTOS);
    }


    @PostMapping("")
    public ResponseEntity<MobileDTO> create(@Valid @RequestBody CreateMobileRequest request) {
        return ResponseEntity.ok(mobileService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseItemResponse<MobileDTO>> getMobileById(@PathVariable("id") Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Mobile mobile = mobileService.findById(id);
        BaseItemResponse<MobileDTO> response = new BaseItemResponse<>();
        response.setSuccess(true);
        response.setData(modelMapper.map(mobile, MobileDTO.class));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteById(@PathVariable("id") Long id) {
        ModelMapper modelMapper = new ModelMapper();
        mobileService.deleteById(id);
        BaseResponse response = new BaseResponse();
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/filter2")
    public ResponseEntity<BaseListItemResponse<MobileDTO>> filterMobileList(@Validated @RequestBody FilterMobileRequest request) throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        Page<Mobile> mobilePage = mobileService.filterMobileList(
                request.getTypeFrom(),
                request.getTypeTo(),
                request.getActive(),
                !Strings.isEmpty(request.getDateFrom()) ? MyUtils.convertDateFromString(request.getDateFrom(), DateTimeConstant.DATE_FORMAT) : null,
                !Strings.isEmpty(request.getDateTo()) ? MyUtils.convertDateFromString(request.getDateTo(), DateTimeConstant.DATE_FORMAT) : null,
                request.getName(),
                request.getStart(),
                request.getLimit()
        );

        List<MobileDTO> mobileDTOS = mobilePage.getContent().stream().map(
                mobile -> modelMapper.map(mobile, MobileDTO.class)
        ).collect(Collectors.toList());
        BaseListItemResponse<MobileDTO> response = new BaseListItemResponse<>();
        response.setSuccess(true);
        response.setResult(mobileDTOS, mobilePage.getTotalElements());
        return ResponseEntity.ok(response);
    }
}
