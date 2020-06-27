package com.zstu.htmg.controller;

import com.zstu.htmg.api.CommonResult;
import com.zstu.htmg.api.MyLog;
import com.zstu.htmg.dto.*;
import com.zstu.htmg.pojo.Employee;
import com.zstu.htmg.service.AdminService;
import com.zstu.htmg.service.EmployeeService;
import com.zstu.htmg.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 14:32
 */
@Controller
@Api(tags = "EmployeeController", description = "员工管理")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    private String GetUsername(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取用户名
        String authHeader = request.getHeader(this.tokenHeader);
        String username=null;
        if (authHeader != null) {
            username = jwtTokenUtil.getUserNameFromToken(authHeader);
        }
        return username;
    }

    @MyLog(operation = "获取指定员工信息",database = "employee,employeetype,user")
    @ApiOperation(value = "获取指定员工信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> getEmployeeById(@PathVariable int id){
        EmployeeInfoDTO answ = null;
        try {
            answ = employeeService.getEmployeeById(id,GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "新增员工",database = "employee,employeetype,user,role")
    @ApiOperation(value = "新增员工")
    @RequestMapping(value = "/insertNewEmployee", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> insertNewEmployee(@RequestBody EmployeeAddDTO employeeAddDTO){
        boolean answ = false;
        try {
            answ = employeeService.insertNewEmployee(employeeAddDTO,GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "根据宾馆获取员工信息",database = "employee,employeetype,user")
    @ApiOperation(value = "根据宾馆获取员工信息")
    @RequestMapping(value = "/byhotel/{hotelid}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> getEmployeeByHotel(@PathVariable int hotelid){
        List<EmployeeInfoDTO> answ = null;
        try {
            answ = employeeService.getEmployeeByHotel(hotelid,GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "获取员工类型和id关系",database = "employeetype")
    @ApiOperation(value = "获取员工类型和id关系")
    @RequestMapping(value = "/employeetype", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> getIdAndType(){
        List<IdTypeDTO> answ = null;
        try {
            answ = employeeService.getIdAndType(GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "获取hotel和id关系",database = "hotel")
    @ApiOperation(value = "获取hotel和id关系")
    @RequestMapping(value = "/hotel", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> getIdAndHotel(){
        List<IdHotelDTO> answ = null;
        try {
            answ = employeeService.getIdAndHotel(GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }
}
