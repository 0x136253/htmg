package com.zstu.htmg.component;

import com.zstu.htmg.mapper.EmployeeMapper;
import com.zstu.htmg.mapper.RoleMapper;
import com.zstu.htmg.pojo.Employee;
import com.zstu.htmg.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 16:11
 */
@Component
public class RoleComponent {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    public int getHotelId(String username){
        Integer temp = employeeMapper.SelectHotelidByUsername(username);
        if (temp == null){
            return  0;
        }
        return temp;
    }

    public String getRoleTypeByUsername(String username){
        return roleMapper.selectRoleTypeByUsername(username);
    }

    public boolean checkRoleLower(String username,String roleType){
        String ownRoleType = getRoleTypeByUsername(username);
        int ownNum = getRoleNum(ownRoleType);
        int targetNum = getRoleNum(roleType);
        if (ownNum>targetNum){
            return true;
        }
        else{
            return false;
        }
    }

    private int getRoleNum(String roleType){
        if ("ROLE_ROOT".equals(roleType)){
            return 5;
        }
        else if ("ROLE_SYSTEM".equals(roleType)){
            return 4;
        }
        else if ("ROLE_HOTEL".equals(roleType)){
            return 3;
        }
        else if ("ROLE_MANAGER".equals(roleType)){
            return 2;
        }
        else if ("ROLE_USER".equals(roleType)){
            return 1;
        }
        else{
            return 0;
        }
    }

    public Role getRole(String roleType){
        if ("ROLE_ROOT".equals(roleType)){
            return getRoleRoot();
        }
        else if ("ROLE_SYSTEM".equals(roleType)){
            return getRoleSystem();
        }
        else if ("ROLE_HOTEL".equals(roleType)){
            return getRoleHotel();
        }
        else if ("ROLE_MANAGER".equals(roleType)){
            return getRoleManager();
        }
        else if ("ROLE_USER".equals(roleType)){
            return getRoleUser();
        }
        else{
            return getRoleUser();
        }
    }

    private Role getRoleRoot(){
        Role role = new Role();
        role.setName("ROOT");
        role.setType("ROLE_ROOT");
        role.setDescription("系统管理员");
        return role;
    }

    private Role getRoleSystem(){
        Role role = new Role();
        role.setName("SYSTEM");
        role.setType("ROLE_SYSTEM");
        role.setDescription("管理员");
        return role;
    }

    private Role getRoleHotel(){
        Role role = new Role();
        role.setName("HOTEL");
        role.setType("ROLE_HOTEL");
        role.setDescription("宾馆主管");
        return role;
    }

    private Role getRoleManager(){
        Role role = new Role();
        role.setName("MANAGER");
        role.setType("ROLE_MANAGER");
        role.setDescription("宾馆管理员");
        return role;
    }

    private Role getRoleUser(){
        Role role = new Role();
        role.setName("USER");
        role.setType("ROLE_USER");
        role.setDescription("员工");
        return role;
    }
}
