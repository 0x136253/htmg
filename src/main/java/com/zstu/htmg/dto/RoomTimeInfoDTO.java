package com.zstu.htmg.dto;

import java.util.Date;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/28 14:02
 */
public class RoomTimeInfoDTO {
    private int id;
    private Date checkintime;
    private Date checkouttime;
    private Date duetime;

    @Override
    public String toString() {
        return "RoomTimeInfoDTO{" +
                "id=" + id +
                ", checkintime=" + checkintime +
                ", checkouttime=" + checkouttime +
                ", duetime=" + duetime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(Date checkintime) {
        this.checkintime = checkintime;
    }

    public Date getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(Date checkouttime) {
        this.checkouttime = checkouttime;
    }

    public Date getDuetime() {
        return duetime;
    }

    public void setDuetime(Date duetime) {
        this.duetime = duetime;
    }
}
