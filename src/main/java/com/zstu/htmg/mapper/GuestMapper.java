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

    @Select("select * from guest where id = #{id,jdbcType = INTEGER}")
    @Results(id = "GuestDetailDTOMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "socialId",property = "socialid"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "gender",property = "gender"),
            @Result(column = "isVip",property = "isvip"),
            @Result(column = "vipId",property = "vipid"),
            @Result(column = "id",property = "times",many = @Many(select = "com.zstu.htmg.mapper.GuestListMapper.selectRoomTimeByGuestID"))
    })
    GuestDetailDTO selectGuestDetailById(@Param("id") Integer id);
}