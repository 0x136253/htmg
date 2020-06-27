package com.zstu.htmg.service.Impl;

import com.zstu.htmg.component.RoleComponent;
import com.zstu.htmg.dto.AllRoomInfoDTO;
import com.zstu.htmg.dto.EmployeeInfoDTO;
import com.zstu.htmg.dto.RoomDetailDTO;
import com.zstu.htmg.dto.RoomInfoDTO;
import com.zstu.htmg.mapper.RoleMapper;
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
    @Autowired
    private RoleComponent roleComponent;

    @Override
    public List<AllRoomInfoDTO> getAllRoomsInfo() throws Exception {
        return roomMapper.selectAllRoomInfo();
    }

    @Override
    public List<RoomInfoDTO> getRoomsInfo(int hotelid,String username) throws Exception {
        int userHotelId = roleComponent.getHotelId(username);
        if (userHotelId != 0 && userHotelId != hotelid){
            throw new Exception("you can't get room information who is't in your hotel");
        }
        return roomMapper.selectRoomInfoByHotelId(hotelid);
    }

    @Override
    public RoomDetailDTO getRoomDetail(int id, String username) throws Exception {
        RoomDetailDTO answ = roomMapper.selectRoomDetailByID(id);
        int userHotelId = roleComponent.getHotelId(username);
        if (userHotelId != 0 && userHotelId != answ.getHotelid()){
            throw new Exception("you can't get room information who is't in your hotel");
        }
        return answ;
}
}
