package com.zstu.htmg.dto;

import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 1:24
 */
public class AllRoomInfoDTO {
    private Integer id;
    private Integer hotelId;
    private String hotelName;
    private String roomid;
    private String name;
    private String type;
    private Integer story;
    private List<String> tags;

    @Override
    public String toString() {
        return "AllRoomInfoDTO{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", roomid='" + roomid + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", story=" + story +
                ", tags=" + tags +
                '}';
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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
}
