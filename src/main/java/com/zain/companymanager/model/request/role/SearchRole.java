package com.zain.companymanager.model.request.role;

import com.zain.companymanager.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRole extends SearchDto {
    private String code;
    private String name;
}
