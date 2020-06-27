package com.zstu.htmg.service;



import com.zstu.htmg.dto.PasswordChangeDTO;
import com.zstu.htmg.dto.UserInfoDTO;
import com.zstu.htmg.dto.UsernameChangeDTO;
import com.zstu.htmg.pojo.User;

import java.util.List;

public interface AdminService {

    String login(String username, String password) throws Exception;
    User getAdminByUsername(String username) throws Exception;
    User register(User user,String Type) throws Exception;
    UserInfoDTO getUserInfo(String username) throws Exception;
    void logout(String username) throws Exception;

    boolean usernameChange(UsernameChangeDTO usernameChangeDTO,String oldUsername)throws Exception;

    boolean passwordChange(PasswordChangeDTO passwordChangeDTO,String username)throws Exception;
}
