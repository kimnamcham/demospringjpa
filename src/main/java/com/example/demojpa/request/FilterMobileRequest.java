package com.example.demojpa.request;

import com.example.demojpa.validator.DateValidateAnnotation;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class FilterMobileRequest {
    private Integer typeFrom;
    private Integer typeTo;
    private Integer active;
    @DateValidateAnnotation(message = "Định dạng ngày tháng phải là dd/MM/YYYY")
    private String dateFrom;
    @DateValidateAnnotation(message = "Định dạng ngày tháng phải là dd/MM/YYYY")
    private String dateTo;
    private String name;
    @NotNull(message = "Start không được để trống")
    private Integer start;
    @NotNull(message = "Limit không được để trống")
    private Integer limit;
}
