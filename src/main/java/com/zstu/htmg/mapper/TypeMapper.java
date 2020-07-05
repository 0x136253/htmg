package com.zstu.htmg.mapper;

import com.zstu.htmg.dto.IdTypeDTO;
import com.zstu.htmg.dto.IdTypeNumDTO;
import com.zstu.htmg.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TypeMapper {
    @Select("select distinct type.id,type.name,type.num from room left join roomState on room.id = roomState.roomId left join type on room.typeid = type.id where roomState.roomState=1 and room.hotelId = #{hotelId,jdbcType = INTEGER}")
    @Results(id = "IdTypeNumDTOMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "type"),
            @Result(column = "num", property = "capacity")
    })
    List<IdTypeNumDTO> SelectEmptyRoomIdAndType(@Param("hotelId") Integer hotelId);

    @Select("select type.id,type.name from type")
    @Results(id = "IdTypeDTOMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "type")
    })
    List<IdTypeDTO> SelectRoomIdAndType();
}