package com.zstu.htmg.service.Impl;

import com.zstu.htmg.dto.AllRoomInfoDTO;
import com.zstu.htmg.dto.RoomDetailDTO;
import com.zstu.htmg.dto.RoomInfoDTO;
import com.zstu.htmg.mapper.RoomMapper;
import com.zstu.htmg.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 1:35
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<AllRoomInfoDTO> getAllRoomsInfo() throws Exception {
        return roomMapper.selectAllRoomInfo();
    }

    @Override
    public List<RoomInfoDTO> getRoomsInfo(int hotelid) throws Exception {
        return roomMapper.selectRoomInfoByHotelId(hotelid);
    }

    @Override
    public RoomDetailDTO getRoomDetail(int id, String username) throws Exception {
        /**
         * wait for check permiss for username
         */
        return roomMapper.selectRoomDetailByID(id);
    }
}
