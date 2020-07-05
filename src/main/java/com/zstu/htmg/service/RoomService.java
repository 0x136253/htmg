package com.zstu.htmg.service;

import com.zstu.htmg.dto.*;

import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 0:35
 */
public interface RoomService {
    List<AllRoomInfoDTO> getAllRoomsInfo() throws Exception;

    List<RoomInfoDTO> getRoomsInfo(int hotelid,String username) throws Exception;

    RoomDetailDTO getRoomDetail(int id,String username) throws Exception;

    RoomPriceDTO getRoomPrice(RoomPriceInDTO roomPriceInDTO,String username) throws Exception;

    Boolean RoomCheckOut(int roomId, String username) throws Exception;

    List<IdTypeDTO> allType() throws Exception;

    RoomDetailDTO changeRoomInfo(RoomChangeDTO roomChangeDTO, String username) throws Exception;
}
