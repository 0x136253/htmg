package com.zstu.htmg.pojo;

import java.io.Serializable;
import java.util.Date;

public class GuestList implements Serializable {
    private Integer id;
    private Date checkintime;
    private Date checkouttime;
    private Date duetime;
    private Integer guestid;
    private Integer roomid;
    private Boolean isover;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getGuestid() {
        return guestid;
    }

    public void setGuestid(Integer guestid) {
        this.guestid = guestid;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Boolean getIsover() {
        return isover;
    }

    public void setIsover(Boolean isover) {
        this.isover = isover;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "GuestList{" +
                "id=" + id +
                ", checkintime=" + checkintime +
                ", checkouttime=" + checkouttime +
                ", duetime=" + duetime +
                ", guestid=" + guestid +
                ", roomid=" + roomid +
                ", isover=" + isover +
                '}';
    }
}