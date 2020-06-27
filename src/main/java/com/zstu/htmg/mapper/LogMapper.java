package com.zstu.htmg.mapper;

import com.zstu.htmg.pojo.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    @Insert("insert into Log(UserID,OperationName,ClassName,Method,Params,DatabaseName,OperationTime,IP,IsSuccess) " +
            "values(#{userid,jdbcType=VARCHAR},#{operationname,jdbcType=VARCHAR},#{classname,jdbcType=VARCHAR},#{method,jdbcType=VARCHAR},#{params,jdbcType=VARCHAR},#{databaseName,jdbcType=VARCHAR},#{operationtime,jdbcType=TIMESTAMP},#{ip,jdbcType=VARCHAR},#{issuccess,jdbcType=BIT})")
    void insertSelectiveWithoutID(Log sysLog);
}