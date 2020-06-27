package com.zstu.htmg.mapper;

import com.zstu.htmg.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("select role.* from role left join user on user.ID = role.UserID where user.username = #{username};")
    @Results(id = "roleMap", value={
            @Result(id = true, column = "ID", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type"),
            @Result(column = "UserID", property = "userid"),
            @Result(column = "description", property = "description")

    })
    List<Role> selectByUsername(@Param("username") String username);

    @Insert("insert into role(name,type,UserID,description) values(#{name},#{type},#{userid},#{description})")
    void insertSelectiveWithoutID(Role role);

    @Select("select * from role where UserID = #{userID}")
    @ResultMap(value = "roleMap")
    List<Role> selectByUserID(@Param("userID") String userID);

    @Select("select role.type from role left join user on user.ID = role.UserID where user.username = #{username};")
    String selectRoleTypeByUsername(@Param("username") String username);
}