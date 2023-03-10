package com.example.demojpa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class MobileDTO {
    private Long id;

    private String name;

    private String description;

    @JsonFormat(pattern = "dd/MM/YYYY HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Timestamp createdTime;

}
