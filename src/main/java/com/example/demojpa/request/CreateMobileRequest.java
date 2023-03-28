package com.example.demojpa.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateMobileRequest {
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 6, max = 200, message = "Tên phải tối thiểu là 6 kí tự, tối đa 200 ký tự")
    private String name;

    private String description;

}
