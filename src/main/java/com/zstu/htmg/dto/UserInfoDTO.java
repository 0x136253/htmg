package com.zstu.htmg.dto;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 22:24
 */
public class UserInfoDTO {
    private String name;
    private String phone;
    private String type;
    private String hotel;
    private String role;

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                ", hotel='" + hotel + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
