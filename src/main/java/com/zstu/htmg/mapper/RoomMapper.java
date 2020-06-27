package com.zstu.htmg.mapper;

import com.zstu.htmg.dto.AllRoomInfoDTO;
import com.zstu.htmg.dto.RoomDetailDTO;
import com.zstu.htmg.dto.RoomInfoDTO;
import com.zstu.htmg.pojo.Room;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import sun.awt.image.IntegerComponentRaster;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface RoomMapper {

    @Select("select room.id,room.roomId,room.name,type.name as type,room.story from room left join type on type.id = room.typeid where room.hotelid = #{hotelid,jdbcType=INTEGER}")
    @Results(id = "roomInfoDTOMap", value={
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roomId", property = "roomid"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type"),
            @Result(column = "story", property = "story"),
            @Result(column = "id", property = "tags",many = @Many(select = "selectStatesByRoomID"))
    })
    List<RoomInfoDTO> selectRoomInfoByHotelId(@Param("hotelid") Integer hotelid);

    @Select("select room.id,room.hotelid,hotel.name as 'hotelName',room.roomId,room.name,type.name as type,room.story from room left join hotel on hotel.id = room.hotelid left join type on type.id = room.typeid")
    @Results(id = "allRoomInfoDTOMap",value={
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "hotelid", property = "hotelId"),
            @Result(column = "hotelName", property = "hotelName"),
            @Result(column = "roomId", property = "roomid"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type"),
            @Result(column = "story", property = "story"),
            @Result(column = "id", property = "tags",many = @Many(select = "selectStatesByRoomID"))
    })
    List<AllRoomInfoDTO> selectAllRoomInfo();

    @Select("Select roomState.roomState from roomState where roomId = #{roomId,jdbcType=INTEGER}")
    List<String> selectStatesByRoomID(@Param("roomId")Integer roomId);

    @Select("select room.id,room.roomId,room.name,type.name as type,room.story,price.basePrice from room left join type on type.id = room.typeid left join price on price.id = type.priceId where room.id = #{id,jdbcType=INTEGER}")
    @Results(id = "roomDetailDTOMap",value={
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roomId", property = "roomid"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type"),
            @Result(column = "story", property = "story"),
            @Result(column = "id", property = "tags",many = @Many(select = "selectStatesByRoomID")),
            @Result(column = "basePrice", property = "price",javaType = BigDecimal.class),
            @Result(column = "id", property = "guests",many = @Many(select = "com.zstu.htmg.mapper.GuestMapper.selectGuestByroomId"))

    })
    RoomDetailDTO selectRoomDetailByID(@Param("id")Integer id);
}