package com.zain.companymanager.dto;

import com.zain.companymanager.model.response.UserSearchResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageUser {
    List<UserSearchResponse> userDtoList;
    int totalpage;
}
