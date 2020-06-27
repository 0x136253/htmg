package com.zstu.htmg;

import com.zstu.htmg.component.RoleComponent;
import com.zstu.htmg.dto.AllRoomInfoDTO;
import com.zstu.htmg.dto.RoomDetailDTO;
import com.zstu.htmg.dto.RoomInfoDTO;
import com.zstu.htmg.mapper.*;
import com.zstu.htmg.pojo.Employee;
import com.zstu.htmg.pojo.Role;
import com.zstu.htmg.pojo.Room;
import com.zstu.htmg.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class HtmgApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RoleComponent roleComponent;
    @Test
    void UserRoleSelectTest() {
        User user = userMapper.selectByUsername("Anon").get(0);
        System.out.println(user);
        List<Role> role = roleMapper.selectByUsername("Anon");
        System.out.println(role);
        role = roleMapper.selectByUserID("a7a06815-5291-5396-928b-4438aa276d48");
        System.out.println(role);
        String type = roleMapper.selectRoleTypeByUsername("Anon");
        System.out.println(type);
        System.out.println(employeeMapper.SelectHotelidByUsername("555736452920"));
        System.out.println(employeeMapper.SelectHotelidByUsername("Anon"));
        System.out.println(roleComponent.getHotelId("Anon"));
    }

    @Test
    void UserRoleInsertTest() {
        String uuid = UUID.randomUUID().toString();
        User user = new User();
        user.setId(uuid);
        user.setUsername("kolulu");
        user.setPassword("$2a$10$cYhuIpHbzChkwNaXo25Ar.6RPmMM7WNUrTx17EUhYF5HUfV/qZefe");
        userMapper.insert(user);
        Role role = new Role();
        role.setUserid(uuid);
        role.setDescription("管理员");
        role.setName("ADMIN");
        role.setType("ROLE_ADMIN");
        roleMapper.insertSelectiveWithoutID(role);
    }

    @Test
    void RoomSelectTest() {
//        List<String> stringList = roomMapper.selectTagsByHotelId(101,10002);
//        for (String record:stringList){
//            System.out.println(record);
//        }
        RoomDetailDTO roomDetailDTO = roomMapper.selectRoomDetailByID(101);
        System.out.println(roomDetailDTO);
//        List<RoomInfoDTO> roomInfoDTOList = roomMapper.selectRoomInfoByHotelId(10002);
//        for (RoomInfoDTO record:roomInfoDTOList){
//            System.out.println(record);
//        }
//
//        List<AllRoomInfoDTO> allRoomInfoDTOList = roomMapper.selectAllRoomInfo();
//        for (AllRoomInfoDTO record:allRoomInfoDTOList){
//            System.out.println(record);
//        }
    }

    @Test
    void GuestSelectTest(){
        System.out.println(guestMapper.selectGuestById(2));
    }
}
