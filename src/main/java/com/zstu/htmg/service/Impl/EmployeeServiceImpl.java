package com.zstu.htmg.service.Impl;

import com.zstu.htmg.dto.EmployeeAddDTO;
import com.zstu.htmg.dto.EmployeeInfoDTO;
import com.zstu.htmg.dto.IdHotelDTO;
import com.zstu.htmg.dto.IdTypeDTO;
import com.zstu.htmg.mapper.EmployeeMapper;
import com.zstu.htmg.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:34
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * need check for permission(only useful own hotel)
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public EmployeeInfoDTO getEmployeeById(int id) throws Exception {
        return employeeMapper.SelectEmployeeInfoById(id);
    }

    /**
     * need check for permission (RoleType)
     * @param employeeAddDTO
     * @return
     * @throws Exception
     */
    @Override
    public Boolean insertNewEmployee(EmployeeAddDTO employeeAddDTO) throws Exception {
        return null;
    }

    /**
     * need check for permission(only useful own hotel)
     * @param hotelId
     * @return
     * @throws Exception
     */
    @Override
    public List<EmployeeInfoDTO> getEmployeeByHotel(int hotelId) throws Exception {
        return employeeMapper.SelectEmployeeInfoByHotelid(hotelId);
    }

    /**
     * need check for permission(only get lower RoleType)
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public List<IdTypeDTO> getIdAndType(String username) throws Exception {
        return employeeMapper.SelectIdAndType();
    }


    @Override
    public List<IdHotelDTO> getIdAndHotel(String username) throws Exception {
        return employeeMapper.SelectIdAndHotel();
    }
}
