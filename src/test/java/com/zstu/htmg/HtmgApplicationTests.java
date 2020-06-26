package com.zstu.htmg;

import com.zstu.htmg.mapper.RoleMapper;
import com.zstu.htmg.mapper.UserMapper;
import com.zstu.htmg.pojo.Role;
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
    @Test
    void UserRoleSelectTest() {
        User user = userMapper.selectByUsername("Anon").get(0);
        System.out.println(user);
        List<Role> role = roleMapper.selectByUsername("Anon");
        System.out.println(role);
        role = roleMapper.selectByUserID("a7a06815-5291-5396-928b-4438aa276d48");
        System.out.println(role);
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

}
