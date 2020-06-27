package com.zstu.htmg.mapper;

import com.zstu.htmg.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    @Results(id = "userMap", value={
            @Result(id = true, column = "ID", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password")
    })
    List<User> selectByUsername(@Param("username") String username);

    @Insert("insert into user(ID,username,password) values(#{id},#{username},#{password})")
    void insert(User user);

    @Select("select ID from user where username = #{username}")
    List<String> selectUserIDByUsername(@Param("username")String username);
}