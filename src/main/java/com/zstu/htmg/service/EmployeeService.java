package com.zstu.htmg.service;

import com.zstu.htmg.dto.EmployeeAddDTO;
import com.zstu.htmg.dto.EmployeeInfoDTO;
import com.zstu.htmg.dto.IdHotelDTO;
import com.zstu.htmg.dto.IdTypeDTO;
import com.zstu.htmg.pojo.Employee;
import com.zstu.htmg.pojo.Guest;

import java.util.List;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:33
 */
public interface EmployeeService {
    EmployeeInfoDTO getEmployeeById(int id,String username) throws Exception;
    Boolean insertNewEmployee(EmployeeAddDTO employeeAddDTO,String username) throws Exception;
    List<EmployeeInfoDTO> getEmployeeByHotel(int hotelId,String username) throws Exception;
    List<IdTypeDTO> getIdAndType(String username) throws Exception;
    List<IdHotelDTO> getIdAndHotel(String username) throws Exception;
}
