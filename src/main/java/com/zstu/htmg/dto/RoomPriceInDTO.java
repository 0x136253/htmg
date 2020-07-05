package com.zstu.htmg.dto;

import java.math.BigDecimal;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/30 21:55
 */
public class RoomPriceInDTO {
    private int roomId;
    private BigDecimal levelOffsetRadio;
    private BigDecimal basePrice;
    private BigDecimal OffsetRadio;

    @Override
    public String toString() {
        return "RoomPriceInDTO{" +
                "roomId=" + roomId +
                ", levelOffsetRadio=" + levelOffsetRadio +
                ", basePrice=" + basePrice +
                ", OffsetRadio=" + OffsetRadio +
                '}';
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public BigDecimal getLevelOffsetRadio() {
        return levelOffsetRadio;
    }

    public void setLevelOffsetRadio(BigDecimal levelOffsetRadio) {
        this.levelOffsetRadio = levelOffsetRadio;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getOffsetRadio() {
        return OffsetRadio;
    }

    public void setOffsetRadio(BigDecimal offsetRadio) {
        OffsetRadio = offsetRadio;
    }
}
