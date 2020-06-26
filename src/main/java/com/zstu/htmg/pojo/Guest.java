package com.zstu.htmg.pojo;

import java.io.Serializable;

public class Guest implements Serializable {
    private Integer id;
    private String name;
    private String socialid;
    private String phone;
    private String gender;
    private Boolean isvip;
    private Integer vipid;
    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public String getSocialid() {
        return socialid;
    }

    public void setSocialid(String socialid) {
        this.socialid = socialid == null ? null : socialid.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
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

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", socialid='" + socialid + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", isvip=" + isvip +
                ", vipid=" + vipid +
                '}';
    }
}