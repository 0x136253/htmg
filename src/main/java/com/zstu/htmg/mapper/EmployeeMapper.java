package com.zstu.htmg.mapper;

import com.zstu.htmg.dto.EmployeeAddDTO;
import com.zstu.htmg.dto.EmployeeInfoDTO;
import com.zstu.htmg.dto.IdHotelDTO;
import com.zstu.htmg.dto.IdTypeDTO;
import com.zstu.htmg.pojo.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select employee.id,employee.name,employee.phone,employeetype.name as 'type',employee.hotelid,hotel.name as 'hotelName',user.username from employee left join user on employee.userid = user.id left join hotel on employee.hotelid = hotel.id left join employeetype on employeetype.id = employee.typeid where employee.id = #{id,jdbcType=INTEGER}")
    @Results(id = "employeeInfoDTOMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "type",property = "type"),
            @Result(column = "hotelid",property = "hotelid"),
            @Result(column = "hotelName",property = "hotelName"),
            @Result(column = "username",property = "username")

    })
    EmployeeInfoDTO SelectEmployeeInfoById(@Param("id")Integer id);

    void insertSelectiveWithoutID(Employee employee);

    @Select("select employee.id,employee.name,employee.phone,employeetype.name as 'type',employee.hotelid,hotel.name as 'hotelName',user.username from employee left join user on employee.userid = user.id left join hotel on employee.hotelid = hotel.id left join employeetype on employeetype.id = employee.typeid where employee.hotelid = #{hotelId,jdbcType=INTEGER}")
    @ResultMap(value = "employeeInfoDTOMap")
    List<EmployeeInfoDTO> SelectEmployeeInfoByHotelid(@Param("hotelId") Integer hotelId);


    @Select("select id,name as 'type' from employeetype")
    @Results(id = "IdTypeDTOMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "type",property = "type")
    })
    List<IdTypeDTO> SelectIdAndType();


    @Select("select id,name from hotel")
    @Results(id = "IdHotelDTOMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "hotel")
    })
    List<IdHotelDTO> SelectIdAndHotel();
}