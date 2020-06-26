package com.zstu.htmg.dto;


import com.zstu.htmg.pojo.User;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: Anonsmd
 * @Date: 2020/1/10 16:55
 */
public class UserRegisterAdminDTO {
    @ApiModelProperty(value = "用户名Username", required = true)
    private String Username;
    @ApiModelProperty(value = "Password", required = true)
    private String Password;
    @ApiModelProperty(value = "姓名", required = true)
    private String Name;
    @ApiModelProperty(value = "电话", required = true)
    private String Tel;
    @ApiModelProperty(value = "Email", required = true)
    private String Email;
    @ApiModelProperty(value = "roleType", required = true)
    private String RoleType;

    public User ToUser(){
        User user= new User();
        user.setUsername(Username);
        user.setPassword(Password);
        return user;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRoleType() {
        return RoleType;
    }

    public void setRoleType(String roleType) {
        RoleType = roleType;
    }
}
