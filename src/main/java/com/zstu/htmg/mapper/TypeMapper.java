package com.zstu.htmg.mapper;

import com.zstu.htmg.dto.IdTypeDTO;
import com.zstu.htmg.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface TypeMapper {
    @Select("select distinct type.id,type.name from room left join roomState on room.id = roomState.roomId left join type on room.typeid = type.id where roomState.roomState=1 and room.hotelId = #{hotelId,jdbcType = INTEGER}")
    @Results(id = "IdTypeDTOMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "type")
    })
    List<IdTypeDTO> SelectEmptyRoomIdAndType(@Param("hotelId") Integer hotelId);
}