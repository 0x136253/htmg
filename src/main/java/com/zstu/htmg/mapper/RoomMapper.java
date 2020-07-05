package com.zstu.htmg.mapper;

import com.zstu.htmg.dto.*;
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

    @Select("select room.id,room.roomId,room.hotelId,room.name,type.name as type,type.num,room.story,price.basePrice from room left join type on type.id = room.typeid left join price on price.id = type.priceId where room.id = #{id,jdbcType=INTEGER}")
    @Results(id = "roomDetailDTOMap",value={
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roomId", property = "roomid"),
            @Result(column = "hotelId", property = "hotelid"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type"),
            @Result(column = "num", property = "capacity"),
            @Result(column = "story", property = "story"),
            @Result(column = "id", property = "tags",many = @Many(select = "selectStatesByRoomID")),
            @Result(column = "basePrice", property = "price",javaType = BigDecimal.class),
            @Result(column = "id", property = "guests",many = @Many(select = "com.zstu.htmg.mapper.GuestMapper.selectGuestByroomId"))

    })
    RoomDetailDTO selectRoomDetailByID(@Param("id")Integer id);

    @Select("select room.id,room.roomId,room.hotelId,room.name,type.name as 'type',type.num,room.story,price.basePrice from room left join type on type.id = room.typeid left join roomState on room.id = roomState.roomId left join price on price.id = type.priceId where room.typeid =#{typeId,jdbcType=INTEGER} and roomState.roomState =1 and room.hotelId = #{hotelId,jdbcType=INTEGER}")
    @ResultMap(value = "roomDetailDTOMap")
    List<RoomDetailDTO> selectEmptyRoomByTypeIdAndHotelId(@Param("typeId")Integer typeId,@Param("hotelId")Integer hotelId);


    @Select("select guest.Name,level.levelOffsetRadio,price.basePrice,OffsetRadio,to_seconds(Now())-to_seconds(guestlist.checkInTime) as times from guestlist  left join room on room.id = guestlist.roomId left join type on room.typeid = type.id left join price on type.priceId = price.id left join guest on guestlist.guestId = guest.id left join level on level.id = guest.vipId where isnull(guestlist.checkoutTime) and guestlist.roomId = #{roomId}")
    @Results(id = "RoomPriceDTOMap",value={
            @Result(id = true, column = "Name", property = "Name"),
            @Result(column = "levelOffsetRadio", property = "levelOffsetRadio"),
            @Result(column = "basePrice", property = "basePrice"),
            @Result(column = "OffsetRadio", property = "OffsetRadio"),
            @Result(column = "times", property = "hours"),
    })
    List<RoomPriceDTO> selectPriceByRoomId(@Param("roomId") Integer roomId);

    @Update("update room set id = #{newId},roomId=#{roomId},name = #{name},typeid=#{typeId} where id = #{oldId}")
    void updateRoomInfo(Integer hotelId,Integer newId,Integer oldId,String roomId,String name,Integer typeId);
}