package com.zstu.htmg.service;



import com.zstu.htmg.pojo.User;

import java.util.List;

public interface AdminService {

    String login(String username, String password) throws Exception;
    User getAdminByUsername(String username) throws Exception;
    User register(User user,String Type) throws Exception;

    void logout(String username) throws Exception;
}
