package com.zstu.htmg.dto;

import java.math.BigDecimal;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/30 21:55
 */
public class RoomPriceDTO {
    private String Name;
    private BigDecimal levelOffsetRadio;
    private BigDecimal basePrice;
    private BigDecimal OffsetRadio;
    private int hours;

    @Override
    public String toString() {
        return "RoomPriceDTO{" +
                "Name='" + Name + '\'' +
                ", levelOffsetRadio=" + levelOffsetRadio +
                ", basePrice=" + basePrice +
                ", OffsetRadio=" + OffsetRadio +
                ", hours=" + hours +
                '}';
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
