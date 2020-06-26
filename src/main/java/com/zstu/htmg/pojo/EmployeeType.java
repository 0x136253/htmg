package com.zstu.htmg.pojo;

import java.io.Serializable;

public class EmployeeType implements Serializable {
    private Integer id;
    private String name;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "EmployeeType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}