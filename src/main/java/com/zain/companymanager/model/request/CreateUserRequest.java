package com.zain.companymanager.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CreateUserRequest {
    @NotBlank(message = "Họ trống!")
    @Size(max = 256)
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Tên trống!")
    @Size(max = 256)
    private String lastName;

    @NotBlank(message = "Địa chỉ trống!")
    private String address;

    @NotBlank(message = "Email trống!")
    @Size(max = 256)
    @Column(unique = true)
    @Pattern(message = "Email không hợp lệ!", regexp = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")
    private String email;

    private String password;

    @NotBlank(message = "Số điện thoại trống!")
    @Pattern(message = "Số điện thoại không hợp lệ!", regexp = "(\\+84|0)\\d{8,10}")
    private String phone;

    private Date dateBirth;
    private String status;

    private int flag;
}
