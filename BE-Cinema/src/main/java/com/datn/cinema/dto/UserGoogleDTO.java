package com.datn.cinema.dto;

public class UserGoogleDTO {
    private String token;
    private String name;
    private String avatarUrl;
    private String email;

    public UserGoogleDTO() {
    }

    public UserGoogleDTO(String token, String name, String avatarUrl, String email) {
        this.token = token;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserGoogleDTO{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
