package com.zstu.htmg.dto;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 22:33
 */
public class PasswordChangeDTO {
    private String newPassword;

    @Override
    public String toString() {
        return "PasswordChangeDTO{" +
                "newPassword='" + newPassword + '\'' +
                '}';
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
