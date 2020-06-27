package com.zstu.htmg.dto;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:55
 */
public class IdHotelDTO {
    private Integer id;
    private String hotel;

    @Override
    public String toString() {
        return "IdHotelDTO{" +
                "id=" + id +
                ", hotel='" + hotel + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
}
