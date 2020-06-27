package com.zstu.htmg.dto;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:45
 */
public class EmployeeAddDTO {
    private String name;
    private String phone;
    private Integer typeid;
    private Integer hotelid;
    private String username;

    @Override
    public String toString() {
        return "EmployeeAddDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", typeid=" + typeid +
                ", hotelid=" + hotelid +
                ", username='" + username + '\'' +
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

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getHotelid() {
        return hotelid;
    }

    public void setHotelid(Integer hotelid) {
        this.hotelid = hotelid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
