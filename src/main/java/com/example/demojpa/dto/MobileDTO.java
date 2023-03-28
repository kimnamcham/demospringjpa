package com.example.demojpa.dto;

import com.example.demojpa.constant.DateTimeConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MobileDTO {
    private Long id;

    private String name;

    private String description;

    @JsonFormat(pattern = DateTimeConstant.DATE_TIME_FORMAT, timezone = DateTimeConstant.TIME_ZONE)
    private Timestamp createdTime;

    private Long cartId;
}
