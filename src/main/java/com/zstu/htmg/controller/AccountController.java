package com.zstu.htmg.controller;


import com.zstu.htmg.api.CommonResult;
import com.zstu.htmg.api.MyLog;
import com.zstu.htmg.component.RoleComponent;
import com.zstu.htmg.dto.*;
import com.zstu.htmg.pojo.User;
import com.zstu.htmg.service.AdminService;
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
import java.util.Map;

@Controller
@Api(tags = "AccountController", description = "账户管理[非关键、待修改]")
@RequestMapping("/admin")
public class AccountController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RoleComponent roleComponent;
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

    @MyLog(operation = "登录",database = "user,role")
    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> login(@RequestBody UserLoginDTO userLoginDTO){
        Map<String, Object> tokenMap = new HashMap<>();
        String token = null;
        try {
            token = adminService.login(userLoginDTO.getUsername(),userLoginDTO.getPassword());
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        tokenMap.put("token", token);
        tokenMap.put("RoleType", roleComponent.getRoleTypeByUsername(userLoginDTO.getUsername()));
        tokenMap.put("HotelId", roleComponent.getHotelId(userLoginDTO.getUsername()));
        return CommonResult.success(tokenMap);
    }

    @MyLog(operation = "获取账户信息",database = "user,role,employee,employeetype,hotel")
    @ApiOperation(value = "获取账户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER','USER')")
    public ResponseEntity<Map<String,Object>> getUserInfo(){
        UserInfoDTO user = null;
        try {
            user = adminService.getUserInfo(GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(user);
    }

    @MyLog(operation = "修改用户名",database = "user,role,employee,employeetype,hotel")
    @ApiOperation(value = "修改用户名")
    @RequestMapping(value = "/usernameChange", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER','USER')")
    public ResponseEntity<Map<String,Object>> usernameChange(@RequestBody UsernameChangeDTO usernameChangeDTO){
        boolean answ = false;
        try {
            answ = adminService.usernameChange(usernameChangeDTO,GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "修改密码",database = "user,role,employee,employeetype,hotel")
    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT','SYSTEM','HOTEL','MANAGER','USER')")
    public ResponseEntity<Map<String,Object>> passwordChange(@RequestBody PasswordChangeDTO passwordChangeDTO){
        boolean answ = false;
        try {
            answ = adminService.passwordChange(passwordChangeDTO,GetUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(answ);
    }

    @MyLog(operation = "注册",database = "user,role")
    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ROOT')")
    public ResponseEntity<Map<String,Object>> registerAdmin(@RequestBody UserRegisterAdminDTO userRegisterAdminDTO){
        User user = null;
        try {
            user = adminService.register(userRegisterAdminDTO.ToUser(),userRegisterAdminDTO.getRoleType());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(user);
    }

//    @ApiOperation(value = "ADMIN")
//    @RequestMapping(value = "/ADMIN", method = RequestMethod.GET)
//    @ResponseBody
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public ResponseEntity<Map<String,Object>> ADMIN(){
//        return CommonResult.success("ADMIN");
//    }
//
//    @ApiOperation(value = "ROOT")
//    @RequestMapping(value = "/ADMIN/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Map<String,Object>> ROOT(@PathVariable int id){
//        return CommonResult.success("ROOT");
//    }
//    @ApiOperation(value = "SYSTEM")
//    @RequestMapping(value = "/SYSTEM", method = RequestMethod.GET)
//    @ResponseBody
//    @PreAuthorize("hasRole('SYSTEM')")
//    public ResponseEntity<Map<String,Object>> SYSTEM(){
//        return CommonResult.success("SYSTEM");
//    }
//    @ApiOperation(value = "USER")
//    @RequestMapping(value = "/USER", method = RequestMethod.GET)
//    @ResponseBody
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<Map<String,Object>> USER(){
//        return CommonResult.success("USER");
//    }
}
