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

    @Select("select guest.* from guest where exists(select guestlist.roomId from guestlist left join room on room.id = guestlist.roomId where guestlist.guestid=guest.id and room.hotelId = #{hotelId} and isnull(guestlist.checkoutTime)) limit #{offset},#{rows}")
    @ResultMap(value = "GuestDetailDTOMap")
    List<GuestDetailDTO> selectGuestByHotelId(@Param("hotelId") Integer hotelId,@Param("offset") Integer offset,@Param("rows") Integer rows );


    @Select("select count(*) from guest where exists(select guestlist.roomId from guestlist left join room on room.id = guestlist.roomId where guestlist.guestid=guest.id and room.hotelId = #{hotelId} and isnull(guestlist.checkoutTime))")
    int selectCountGuestByHotelId(@Param("hotelId") Integer hotelId);


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


    @Insert("insert into guest(name,socialId,phone,gender,vipId) values(#{name},#{socialid},#{phone},#{gender},1)")
    void InsertSelectivwWithoutIdAndIsVipAndVip(Guest guest);

    @Select("select * from guest where phone = #{phone}")
    @ResultMap(value = "GuestDetailDTOMap")
    List<GuestDetailDTO> selectByPhone(@Param("phone") String phone);

    @Select("select * from guest where socialId = #{socialId}")
    @ResultMap(value = "GuestDetailDTOMap")
    List<GuestDetailDTO> selectBySocialId(@Param("socialId") String socialId);

    @Select("select if(count(*)=0,false,true) from guest where socialId = #{socialId}")
    boolean checkBySocialId(@Param("socialId") String socialId);

    /**
     * 需改成存储过程，用事务锁死该表，以防第三则更改,与Insert相结合
     * @return
     */
    @Select("select max(id) from guest")
    int returnId();
}

