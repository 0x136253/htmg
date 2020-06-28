package com.zstu.htmg.mapper;

import com.zstu.htmg.dto.RoomTimeInfoDTO;
import com.zstu.htmg.pojo.GuestList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GuestListMapper {

    @Select("select guestlist.roomId,room.hotelId,guestlist.checkInTime,guestlist.checkoutTime,guestlist.dueTime from guestlist left join room on room.id = guestlist.roomId where guestlist.guestId = #{guestid}")
    @Results(id = "RoomTimeInfoDTOMap",value = {
            @Result(id = true,column = "roomId",property = "id"),
            @Result(column = "hotelId",property = "hotelId"),
            @Result(column = "checkInTime",property = "checkintime"),
            @Result(column = "checkoutTime",property = "checkouttime"),
            @Result(column = "dueTime",property = "duetime")
    })
    List<RoomTimeInfoDTO> selectRoomTimeByGuestID(@Param("guestid") Integer guestid);
}