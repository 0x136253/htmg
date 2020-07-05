package com.zstu.htmg.mapper;

import com.zstu.htmg.dto.UserInfoDTO;
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

    @Select("select employee.name,employee.phone,employeetype.name as 'type',hotel.name as 'hotel',role.type as 'role' from user left join employee on employee.userid = user.id left join employeetype on employeetype.id = employee.typeid left join hotel on hotel.id = employee.hotelid left join role on role.UserID = user.id where user.username=#{username}")
    @Results(id = "UserInfoDTOMap", value={
            @Result(column = "name", property = "name"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "type", property = "type"),
            @Result(column = "hotel", property = "hotel"),
            @Result(column = "role", property = "role")
    })
    UserInfoDTO selectUserInfoByUserName(@Param("username") String username);

    @Update("update user set username=#{newUsername} where username = #{oldUsername}")
    void updateUsername(@Param("oldUsername") String oldUsername,@Param("newUsername") String newUsername);

    @Update("update user set password=#{password} where username = #{username}")
    void updatePassword(@Param("username") String username,@Param("password") String password);
}