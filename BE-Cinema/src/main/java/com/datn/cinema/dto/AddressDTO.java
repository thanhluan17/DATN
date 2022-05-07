package com.datn.cinema.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDTO {
    private String wardName;
    private String districtName;
    private String provinceName;
}
