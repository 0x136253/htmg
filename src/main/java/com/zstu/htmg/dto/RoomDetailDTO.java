package com.zstu.htmg.dto;

import com.zstu.htmg.pojo.Guest;
import io.swagger.models.auth.In;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 1:24
 */
public class RoomDetailDTO {
    private Integer id;
    private String roomid;
    private Integer hotelid;
    private String name;
    private String type;
    private Integer story;
    private List<String> tags;
    private BigDecimal price;
    private List<GuestInfoDTO> guests;

    @Override
    public String toString() {
        return "RoomDetailDTO{" +
                "id=" + id +
                ", roomid='" + roomid + '\'' +
                ", hotelid=" + hotelid +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", story=" + story +
                ", tags=" + tags +
                ", price=" + price +
                ", guests=" + guests +
                '}';
    }

    public Integer getHotelid() {
        return hotelid;
    }

    public void setHotelid(Integer hotelid) {
        this.hotelid = hotelid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStory() {
        return story;
    }

    public void setStory(Integer story) {
        this.story = story;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<GuestInfoDTO> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestInfoDTO> guests) {
        this.guests = guests;
    }
}
