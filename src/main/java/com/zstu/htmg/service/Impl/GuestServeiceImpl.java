package com.zstu.htmg.service.Impl;

import com.zstu.htmg.component.RoleComponent;
import com.zstu.htmg.dto.*;
import com.zstu.htmg.mapper.GuestListMapper;
import com.zstu.htmg.mapper.GuestMapper;
import com.zstu.htmg.mapper.RoomMapper;
import com.zstu.htmg.mapper.TypeMapper;
import com.zstu.htmg.pojo.Guest;
import com.zstu.htmg.pojo.GuestList;
import com.zstu.htmg.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:22
 */
@Service
public class GuestServeiceImpl implements GuestService {
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private GuestListMapper guestListMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private RoleComponent roleComponent;
    @Override
    public Guest getGuestDetail(int id) throws Exception {
        return guestMapper.selectGuestById(id);
    }

    @Override
    public GuestDetailDTO getGuestDetailAndTimeInfo(int id) throws Exception {
        return guestMapper.selectGuestDetailById(id);
    }

    @Override
    public List<GuestDetailDTO> getGuestDetailByHotelId(String username,int pageNum,int pageSize) throws Exception {
        int userHotelId = roleComponent.getHotelId(username);
        if (userHotelId == 0){
            return guestMapper.selectGuestByAll();
        }
        else{
            return guestMapper.selectGuestByHotelId(userHotelId,(pageNum-1)*pageSize,pageSize);
        }
    }

    @Override
    public RoomDetailDTO guestCheckIn(GuestInDTO guestInDTO, String username) throws Exception {
        List<RoomDetailDTO> emptyRoom = roomMapper.selectEmptyRoomByTypeIdAndHotelId(guestInDTO.getTypeId(),roleComponent.getHotelId(username));
        if (emptyRoom.size()==0){
            throw new Exception("No Room Exist");
        }
        RoomDetailDTO targetRoom = emptyRoom.get(0);
        if (targetRoom.getCapacity()<guestInDTO.getGuests().size()){
            throw new Exception("Too Many People");
        }
        for (GuestDTO record:guestInDTO.getGuests()){
            if (guestMapper.checkBySocialId(record.getSocialid())){
                GuestDetailDTO guest = guestMapper.selectBySocialId(record.getSocialid()).get(0);
                GuestList guestList = new GuestList();
                guestList.setDuetime(guestInDTO.getDueTime());
                guestList.setGuestid(guest.getId());
                guestList.setRoomid(targetRoom.getId());
                guestListMapper.InsertSelectiveWithoutIdAndCheckoutTimeAndIsOver(guestList);
            }
            else{
                Guest inguest = record.ToGuest();
                guestMapper.InsertSelectivwWithoutIdAndIsVipAndVip(inguest);
                int inId = guestMapper.returnId();
                GuestList guestList = new GuestList();
                guestList.setDuetime(guestInDTO.getDueTime());
                guestList.setGuestid(inId);
                guestList.setRoomid(targetRoom.getId());
                guestListMapper.InsertSelectiveWithoutIdAndCheckoutTimeAndIsOver(guestList);
            }
        }
        return roomMapper.selectRoomDetailByID(targetRoom.getId());
    }

    @Override
    public List<IdTypeNumDTO> guestRoomType(String username) throws Exception {
//        List<IdTypeDTO> answ = new ArrayList<>();
//        answ.add(new IdTypeDTO());
        return typeMapper.SelectEmptyRoomIdAndType(roleComponent.getHotelId(username));
    }

    @Override
    public List<GuestDetailDTO> searchGuest(String key) throws Exception {
        List<GuestDetailDTO> phoneList = guestMapper.selectByPhone(key);
        List<GuestDetailDTO> socialList = guestMapper.selectBySocialId(key);
        List<GuestDetailDTO> answ = new ArrayList<>();
        answ.addAll(phoneList);
        answ.addAll(socialList);
        if (phoneList.size()==0 && socialList.size() == 0){
            throw new Exception("can't find");
        }
        return answ;
    }
}
