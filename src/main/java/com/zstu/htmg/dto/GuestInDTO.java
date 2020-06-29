package com.zstu.htmg.dto;

import com.zstu.htmg.pojo.Guest;

import java.util.Date;
import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/28 21:56
 */
public class GuestInDTO {
    private List<GuestDTO> guests;
    private Date dueTime;
    private int typeId;

    public List<GuestDTO> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestDTO> guests) {
        this.guests = guests;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "GuestInDTO{" +
                "guests=" + guests +
                ", dueTime=" + dueTime +
                ", typeId=" + typeId +
                '}';
    }
}
