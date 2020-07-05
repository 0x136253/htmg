package com.zstu.htmg.service.Impl;

import com.zstu.htmg.component.RoleComponent;
import com.zstu.htmg.dto.*;
import com.zstu.htmg.mapper.GuestListMapper;
import com.zstu.htmg.mapper.RoleMapper;
import com.zstu.htmg.mapper.RoomMapper;
import com.zstu.htmg.mapper.TypeMapper;
import com.zstu.htmg.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private TypeMapper typeMapper;
    @Autowired
    private GuestListMapper guestListMapper;
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

    @Override
    public RoomPriceDTO getRoomPrice(RoomPriceInDTO roomPriceInDTO,String username) throws Exception {
        int hotelId= roleComponent.getHotelId(username);
        List<RoomPriceDTO> dbPrice = roomMapper.selectPriceByRoomId(hotelId*1000+roomPriceInDTO.getRoomId());
        RoomPriceDTO answ = new RoomPriceDTO();
        answ.setLevelOffsetRadio(new BigDecimal(100));
        for (RoomPriceDTO record:dbPrice){
            if (record.getLevelOffsetRadio().compareTo(answ.getLevelOffsetRadio()) < 0){
                answ.setLevelOffsetRadio(record.getLevelOffsetRadio());
                answ.setName(record.getName());
                answ.setBasePrice(record.getBasePrice());
                answ.setOffsetRadio(record.getOffsetRadio());
                answ.setHours((record.getHours()+3599)/3600);
            }
        }
        if (roomPriceInDTO.getBasePrice().compareTo(new BigDecimal(0)) > 0){
            answ.setBasePrice(roomPriceInDTO.getBasePrice());
        }
        if (roomPriceInDTO.getLevelOffsetRadio().compareTo(new BigDecimal(0)) > 0){
            answ.setLevelOffsetRadio(roomPriceInDTO.getLevelOffsetRadio());
        }
        if (roomPriceInDTO.getOffsetRadio().compareTo(new BigDecimal(0)) > 0){
            answ.setOffsetRadio(roomPriceInDTO.getOffsetRadio());
        }
        return answ;
    }

    @Override
    public Boolean RoomCheckOut(int roomId, String username) throws Exception {
        int hotelId = roleComponent.getHotelId(username);
        roomId = roomId+hotelId*1000;
        guestListMapper.updateRoomStatus(roomId);
        return true;
    }

    @Override
    public List<IdTypeDTO> allType() throws Exception {
        return typeMapper.SelectRoomIdAndType();
    }

    @Override
    public RoomDetailDTO changeRoomInfo(RoomChangeDTO roomChangeDTO,String username) throws Exception {
        int hotelId= roleComponent.getHotelId(username);
        Integer newId = hotelId*1000+Integer.parseInt(roomChangeDTO.getNewRoomId());
        Integer oldId = hotelId*1000+Integer.parseInt(roomChangeDTO.getOldRoomId());
        roomMapper.updateRoomInfo(hotelId,newId,oldId,roomChangeDTO.getNewRoomId(),roomChangeDTO.getName(),roomChangeDTO.getTypeId());
        return roomMapper.selectRoomDetailByID(newId);
    }
}
