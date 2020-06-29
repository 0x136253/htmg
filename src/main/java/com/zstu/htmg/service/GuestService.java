package com.zstu.htmg.service;

import com.zstu.htmg.dto.GuestDetailDTO;
import com.zstu.htmg.dto.GuestInDTO;
import com.zstu.htmg.dto.IdTypeDTO;
import com.zstu.htmg.dto.RoomDetailDTO;
import com.zstu.htmg.pojo.Guest;

import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:21
 */
public interface GuestService {
    Guest getGuestDetail(int id) throws Exception;

    GuestDetailDTO getGuestDetailAndTimeInfo(int id) throws Exception;
    List<GuestDetailDTO> getGuestDetailByHotelId(String username) throws Exception;

    RoomDetailDTO guestCheckIn(GuestInDTO guestInDTO, String getUserusernamename) throws Exception;

    List<IdTypeDTO> guestRoomType(String username) throws Exception;
}
