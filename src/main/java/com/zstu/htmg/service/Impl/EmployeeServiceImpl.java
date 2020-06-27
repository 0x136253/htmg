package com.zstu.htmg.service.Impl;

import com.zstu.htmg.component.RoleComponent;
import com.zstu.htmg.dto.EmployeeAddDTO;
import com.zstu.htmg.dto.EmployeeInfoDTO;
import com.zstu.htmg.dto.IdHotelDTO;
import com.zstu.htmg.dto.IdTypeDTO;
import com.zstu.htmg.mapper.EmployeeMapper;
import com.zstu.htmg.mapper.RoleMapper;
import com.zstu.htmg.mapper.UserMapper;
import com.zstu.htmg.pojo.Employee;
import com.zstu.htmg.pojo.Role;
import com.zstu.htmg.pojo.User;
import com.zstu.htmg.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:34
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleComponent roleComponent;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeInfoDTO getEmployeeById(int id,String username) throws Exception {
        int userHotelId = roleComponent.getHotelId(username);
        EmployeeInfoDTO answ = employeeMapper.SelectEmployeeInfoById(id);
        if (userHotelId != 0 && userHotelId != answ.getHotelid()){
            throw new Exception("you can't get employee information who is't in your hotel");
        }
        return answ;
    }

    @Override
    public Boolean insertNewEmployee(EmployeeAddDTO employeeAddDTO,String username) throws Exception {
        if (!roleComponent.checkRoleLower(username,employeeAddDTO.getRole())){
            throw new Exception("权限不足以创建给角色");
        }
        Employee employee = employeeAddDTO.toEmployee();
        String uuid = UUID.randomUUID().toString();
        User user = new User();
        user.setId(uuid);
        user.setUsername(employee.getPhone());
        user.setPassword("123456");
        if (checkIfUserExist(user.getUsername())) {
            throw new Exception("该账号已存在");
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insert(user);

        Role role = roleComponent.getRole(employeeAddDTO.getRole());
        role.setUserid(uuid);
        roleMapper.insertSelectiveWithoutID(role);

        employee.setUserid(uuid);
        employeeMapper.insertSelectiveWithoutID(employee);
        return true;
    }


    @Override
    public List<EmployeeInfoDTO> getEmployeeByHotel(int hotelId,String username) throws Exception {
        int userHotelId = roleComponent.getHotelId(username);
        if (userHotelId != 0 && userHotelId != hotelId){
            throw new Exception("you can't get employee information who is't in your hotel");
        }
        return employeeMapper.SelectEmployeeInfoByHotelid(hotelId);
    }

    @Override
    public List<IdTypeDTO> getIdAndType(String username) throws Exception {
        return employeeMapper.SelectIdAndType();
    }


    @Override
    public List<IdHotelDTO> getIdAndHotel(String username) throws Exception {
        return employeeMapper.SelectIdAndHotel();
    }

    private boolean checkIfUserExist(String username) throws Exception{
        List<User> userList = userMapper.selectByUsername(username);
        if (userList.size()==0){
            return false;
        }
        return true;
    }
}
