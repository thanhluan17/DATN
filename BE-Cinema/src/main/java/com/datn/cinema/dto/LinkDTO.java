package com.datn.cinema.dto;

public class LinkDTO {
    private String linkName;

    public LinkDTO(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
}
