package com.datn.cinema.dto;

import com.datn.cinema.entity.Ward;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEditDTO {
    private int userId;
    private String name;
    private String avatarUrl;
    private String email;
    private String birthday;
    private String phone;
    private String username;
    private String password;
    private String confirmPassword;
    private String idCard;
    private int gender;
    private Ward ward;
}
