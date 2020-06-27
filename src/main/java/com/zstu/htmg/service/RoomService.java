package com.zstu.htmg.service;

import com.zstu.htmg.dto.AllRoomInfoDTO;
import com.zstu.htmg.dto.RoomDetailDTO;
import com.zstu.htmg.dto.RoomInfoDTO;

import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 0:35
 */
public interface RoomService {
    List<AllRoomInfoDTO> getAllRoomsInfo() throws Exception;

    List<RoomInfoDTO> getRoomsInfo(int hotelid,String username) throws Exception;

    RoomDetailDTO getRoomDetail(int id,String username) throws Exception;
}
