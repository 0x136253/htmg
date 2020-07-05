package com.zstu.htmg.dto;

/**
 * @Author: Anonsmd
 * @Date: 2020/7/4 20:04
 */
public class RoomChangeDTO {
    private String oldRoomId;
    private String newRoomId;
    private String name;
    private int typeId;

    @Override
    public String toString() {
        return "RoomChangeDTO{" +
                "oldRoomId='" + oldRoomId + '\'' +
                ", newRoomId='" + newRoomId + '\'' +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                '}';
    }

    public String getOldRoomId() {
        return oldRoomId;
    }

    public void setOldRoomId(String oldRoomId) {
        this.oldRoomId = oldRoomId;
    }

    public String getNewRoomId() {
        return newRoomId;
    }

    public void setNewRoomId(String newRoomId) {
        this.newRoomId = newRoomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
