package com.zstu.htmg.dto;

import java.util.Date;
import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/28 13:58
 */
public class GuestDetailDTO {
    private Integer id;
    private String name;
    private String socialid;
    private String phone;
    private String gender;
    private Boolean isvip;
    private Integer vipid;
    private List<RoomTimeInfoDTO> rooms;

    @Override
    public String toString() {
        return "GuestDetailDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", socialid='" + socialid + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", isvip=" + isvip +
                ", vipid=" + vipid +
                ", rooms=" + rooms +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialid() {
        return socialid;
    }

    public void setSocialid(String socialid) {
        this.socialid = socialid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public Integer getVipid() {
        return vipid;
    }

    public void setVipid(Integer vipid) {
        this.vipid = vipid;
    }

    public List<RoomTimeInfoDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomTimeInfoDTO> rooms) {
        this.rooms = rooms;
    }
}
