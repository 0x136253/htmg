package com.zstu.htmg.dto;

import com.zstu.htmg.pojo.Guest;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/28 22:11
 */
public class GuestDTO {
    private String name;
    private String socialid;
    private String phone;
    private String gender;

    public Guest ToGuest(){
        Guest answ = new Guest();
        answ.setName(name);
        answ.setSocialid(socialid);
        answ.setPhone(phone);
        answ.setGender(gender);
        return answ;
    }

    @Override
    public String toString() {
        return "GuestDTO{" +
                "name='" + name + '\'' +
                ", socialid='" + socialid + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                '}';
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
}
