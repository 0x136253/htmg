package com.zstu.htmg.dto;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 22:32
 */
public class UsernameChangeDTO {
    private String newUsername;

    @Override
    public String toString() {
        return "UsernameChangeDTO{" +
                "newUsername='" + newUsername + '\'' +
                '}';
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }
}
