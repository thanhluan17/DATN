package com.datn.cinema.dto;

public class UserNoAccountDTO {
    private String name;
    private String email;
    private String idCard;
    private String phone;

    public UserNoAccountDTO() {
    }

    public UserNoAccountDTO(String name, String email, String idCard, String phone) {
        this.name = name;
        this.email = email;
        this.idCard = idCard;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
