package com.zstu.htmg.service;

import com.zstu.htmg.dto.*;
import com.zstu.htmg.pojo.Guest;

import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:21
 */
public interface GuestService {
    Guest getGuestDetail(int id) throws Exception;

    GuestDetailDTO getGuestDetailAndTimeInfo(int id) throws Exception;
    List<GuestDetailDTO> getGuestDetailByHotelId(String username,int pageNum,int pageSize) throws Exception;

    RoomDetailDTO guestCheckIn(GuestInDTO guestInDTO, String getUserusernamename) throws Exception;

    List<IdTypeNumDTO> guestRoomType(String username) throws Exception;

    List<GuestDetailDTO> searchGuest(String key) throws Exception;
}
