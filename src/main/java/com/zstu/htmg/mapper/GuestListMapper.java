package com.zstu.htmg.mapper;

import com.zstu.htmg.dto.RoomTimeInfoDTO;
import com.zstu.htmg.pojo.Guest;
import com.zstu.htmg.pojo.GuestList;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GuestListMapper {

    @Select("select guestlist.roomId,room.hotelId,guestlist.checkInTime,guestlist.checkoutTime,guestlist.dueTime from guestlist left join room on room.id = guestlist.roomId where guestlist.guestId = #{guestid} and isnull(guestlist.checkoutTime)")
    @Results(id = "RoomTimeInfoDTOMap",value = {
            @Result(id = true,column = "roomId",property = "id"),
            @Result(column = "hotelId",property = "hotelId"),
            @Result(column = "checkInTime",property = "checkintime"),
            @Result(column = "checkoutTime",property = "checkouttime"),
            @Result(column = "dueTime",property = "duetime")
    })
    List<RoomTimeInfoDTO> selectRoomTimeByGuestID(@Param("guestid") Integer guestid);

    @Insert("insert into guestlist(checkInTime,dueTime,guestId,roomId) values(Now(),#{duetime},#{guestid},#{roomid})")
    void InsertSelectiveWithoutIdAndCheckoutTimeAndIsOver(GuestList guestList);

    @Update("update guestlist set checkoutTime = Now(),isOver = 1 where isnull(guestlist.checkoutTime) and guestlist.roomId=#{roomId}")
    void updateRoomStatus(@Param("roomId") Integer roomId);
}