package com.zstu.htmg.controller;

import com.zstu.htmg.api.CommonResult;
import com.zstu.htmg.api.MyLog;
import com.zstu.htmg.component.RoleComponent;
import com.zstu.htmg.dto.*;
import com.zstu.htmg.mapper.GuestMapper;
import com.zstu.htmg.pojo.Guest;
import com.zstu.htmg.service.GuestService;
import com.zstu.htmg.service.RoomService;
import com.zstu.htmg.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
 * @Date: 2020/6/27 14:20
 */
@Controller
@Api(tags = "GuestController", description = "宾客管理")
@RequestMapping("/guest")
public class GuestController {
    @Autowired
    private GuestService guestService;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private RoleComponent roleComponent;
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

    @MyLog(operation = "获取指定客人信息",database = "guest")
    @ApiOperation(value = "获取指定客人信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> getGuestDetail(@PathVariable int id){
        Guest answ = null;
        try {
            answ = guestService.getGuestDetail(id);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "获取指定客人信息及入住信息",database = "guest,guestlist")
    @ApiOperation(value = "获取指定客人信息及入住信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> getGuestDetailAndTimeInfo(@PathVariable int id){
        GuestDetailDTO answ = null;
        try {
            answ = guestService.getGuestDetailAndTimeInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "获取指定宾馆客人信息及入住信息",database = "room,guest,guestlist")
    @ApiOperation(value = "获取指定宾馆客人信息及入住信息")
    @RequestMapping(value = "/hotel/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> getGuestByHotel(@PathVariable int pageNum,@PathVariable int pageSize){
        Map<String,Object> answMap = new HashMap<>();
        List<GuestDetailDTO> answ = null;
        try {
            answ = guestService.getGuestDetailByHotelId(GetUsername(),pageNum,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        int total = guestMapper.selectCountGuestByHotelId(roleComponent.getHotelId(GetUsername()));
        answMap.put("list",answ);
        answMap.put("total",total);
        answMap.put("page",(total+pageSize-1)/pageSize);
        return CommonResult.success(answMap);
    }


    @MyLog(operation = "客人入住",database = "guest,guestlist")
    @ApiOperation(value = "客人入住")
    @RequestMapping(value = "/checkin", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> guestCheckIn(@RequestBody GuestInDTO guestInDTO){
        RoomDetailDTO answ = null;
        try {
            answ = guestService.guestCheckIn(guestInDTO,GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "返回房间类型和ID关系",database = "room,type")
    @ApiOperation(value = "返回房间类型和ID关系")
    @RequestMapping(value = "/RoomType", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> guestRoomType(){
        List<IdTypeNumDTO> answ = null;
        try {
            answ = guestService.guestRoomType(GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "根据socialID和phone搜索客人",database = "guest")
    @ApiOperation(value = "根据socialID和phone搜索客人")
    @RequestMapping(value = "/search/{key}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> guestRoomType(@PathVariable String key){
        List<GuestDetailDTO> answ = null;
        try {
            answ = guestService.searchGuest(key);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }
}
