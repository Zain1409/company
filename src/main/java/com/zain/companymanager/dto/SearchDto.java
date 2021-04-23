package com.zain.companymanager.dto;

import lombok.Data;

@Data
public class SearchDto {

    private  Long id;
    private String checkCode;
    private int pageNo;
    private int pageSize;
}
