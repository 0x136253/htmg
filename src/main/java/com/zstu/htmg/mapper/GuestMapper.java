package com.zstu.htmg.mapper;

import com.zstu.htmg.dto.GuestDetailDTO;
import com.zstu.htmg.dto.GuestInfoDTO;
import com.zstu.htmg.pojo.Guest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GuestMapper {

    @Select("select guest.id,guest.name from guestlist left join guest on guest.id = guestlist.guestid where guestlist.roomId=#{roomId,jdbcType=INTEGER} AND guestlist.isOver = 0;")
    @Results(id = "guestInfoDTOMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
//            @Result(column = "socialId",property = "socialid"),
//            @Result(column = "phone",property = "phone"),
//            @Result(column = "gender",property = "gender"),
//            @Result(column = "isVip",property = "isvip"),
//            @Result(column = "vipId",property = "vipid")
    })
    List<GuestInfoDTO> selectGuestByroomId(@Param("roomId") Integer roomId);

    @Select("select * from guest where id = #{id,jdbcType = INTEGER}")
    @Results(id = "guestMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "socialId",property = "socialid"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "gender",property = "gender"),
            @Result(column = "isVip",property = "isvip"),
            @Result(column = "vipId",property = "vipid")
    })
    Guest selectGuestById(@Param("id") Integer id);

    @Select("select guest.* from guest where exists(select guestlist.roomId from guestlist left join room on room.id = guestlist.roomId where guestlist.guestid=guest.id and room.hotelId = #{hotelId})")
    @ResultMap(value = "GuestDetailDTOMap")
    List<GuestDetailDTO> selectGuestByHotelId(@Param("hotelId") Integer hotelId);


    @Select("select guest.* from guest where exists(select guestlist.roomId from guestlist left join room on room.id = guestlist.roomId where guestlist.guestid=guest.id)")
    @ResultMap(value = "GuestDetailDTOMap")
    List<GuestDetailDTO> selectGuestByAll();

    @Select("select * from guest where id = #{id,jdbcType = INTEGER}")
    @Results(id = "GuestDetailDTOMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "socialId",property = "socialid"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "gender",property = "gender"),
            @Result(column = "isVip",property = "isvip"),
            @Result(column = "vipId",property = "vipid"),
            @Result(column = "id",property = "rooms",many = @Many(select = "com.zstu.htmg.mapper.GuestListMapper.selectRoomTimeByGuestID"))
    })
    GuestDetailDTO selectGuestDetailById(@Param("id") Integer id);
}