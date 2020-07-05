package com.zstu.htmg.dto;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:54
 */
public class IdTypeNumDTO {
    private Integer id;
    private String type;
    private int capacity;

    @Override
    public String toString() {
        return "IdTypeNumDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
