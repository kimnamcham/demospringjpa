package com.example.demojpa.controller;

import com.example.demojpa.dto.MobileDTO;
import com.example.demojpa.request.CreateMobileRequest;
import com.example.demojpa.service.MobileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<MobileDTO>> getAllMobile(){
        ModelMapper modelMapper = new ModelMapper();
        List<MobileDTO> mobileDTOS = mobileService.getListMobile().stream().map(
                mobile -> modelMapper.map(mobile, MobileDTO.class)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(mobileDTOS);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MobileDTO>> filterMobile(@RequestParam(value = "type", required = false) Integer type,
                                                        @RequestParam(value = "active", required = false) Integer active ){
        ModelMapper modelMapper = new ModelMapper();
        List<MobileDTO> mobileDTOS = mobileService.filterListMobile(type, active).stream().map(
                mobile -> modelMapper.map(mobile, MobileDTO.class)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(mobileDTOS);
    }

    @PostMapping("")
    public ResponseEntity<MobileDTO> create(@Valid @RequestBody CreateMobileRequest request){
        return ResponseEntity.ok(mobileService.create(request));
    }
}
