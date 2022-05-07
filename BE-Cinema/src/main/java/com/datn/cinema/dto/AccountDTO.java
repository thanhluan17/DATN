package com.datn.cinema.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private String username;
    private String newPassword;
    private String oldPassword;
    private String otp;

    public AccountDTO() {
    }

    public AccountDTO(String username, String newPassword, String oldPassword, String otp) {
        this.username = username;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
