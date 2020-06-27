package com.zstu.htmg.dto;

import com.zstu.htmg.pojo.Employee;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:45
 */
public class EmployeeAddDTO {
    private String name;
    private String phone;
    private Integer typeid;
    private Integer hotelid;
    private String role;

    @Override
    public String toString() {
        return "EmployeeAddDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", typeid=" + typeid +
                ", hotelid=" + hotelid +
                ", role='" + role + '\'' +
                '}';
    }

    public Employee toEmployee(){
        Employee employee = new Employee();
        employee.setName(name);
        employee.setPhone(phone);
        employee.setTypeid(typeid);
        employee.setHotelid(hotelid);
        return employee;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getHotelid() {
        return hotelid;
    }

    public void setHotelid(Integer hotelid) {
        this.hotelid = hotelid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
