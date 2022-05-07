package com.datn.cinema.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPreviewDTO {
    private int userId;
    private String name;
    private String avatarUrl;
    private String email;
    private AddressDTO address;
    private AccountStatusDTO accountStatus;
}
