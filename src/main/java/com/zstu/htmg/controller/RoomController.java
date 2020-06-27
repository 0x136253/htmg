package com.zstu.htmg.controller;

import com.zstu.htmg.api.CommonResult;
import com.zstu.htmg.api.MyLog;
import com.zstu.htmg.dto.AllRoomInfoDTO;
import com.zstu.htmg.dto.RoomDetailDTO;
import com.zstu.htmg.dto.RoomInfoDTO;
import com.zstu.htmg.dto.UserRegisterAdminDTO;
import com.zstu.htmg.pojo.User;
import com.zstu.htmg.service.AdminService;
import com.zstu.htmg.service.RoomService;
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
import java.util.List;
import java.util.Map;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 0:34
 */
@Controller
@Api(tags = "RoomController", description = "客房管理")
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
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


    @MyLog(operation = "获取所有房间信息",database = "room,type，roomState,hotel")
    @ApiOperation(value = "获取所有房间信息")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM')")
    public ResponseEntity<Map<String,Object>> getAllRoomsInfo(){
        List<AllRoomInfoDTO> answ = null;
        try {
            answ = roomService.getAllRoomsInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "获取指定宾馆所有房间信息",database = "room,type，roomState")
    @ApiOperation(value = "获取指定宾馆所有房间信息")
    @RequestMapping(value = "/all/{hotelid}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> getRoomsInfo(@PathVariable int hotelid){
        List<RoomInfoDTO> answ = null;
        try {
            answ = roomService.getRoomsInfo(hotelid);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "获取指定房间信息",database = "room,type，roomState,price,guestlist,guest")
    @ApiOperation(value = "获取指定房间信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER')")
    public ResponseEntity<Map<String,Object>> getRoomDetail(@PathVariable int id){
        RoomDetailDTO answ = null;
        try {
            answ = roomService.getRoomDetail(id,GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }
}
