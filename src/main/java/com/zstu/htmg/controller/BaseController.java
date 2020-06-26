package com.zstu.htmg.controller;


import com.zstu.htmg.api.CommonResult;
import com.zstu.htmg.dto.UserLoginDTO;
import com.zstu.htmg.dto.UserRegisterAdminDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "BaseController", description = "TEST[非关键、待修改]")
@RequestMapping("/admin")
public class BaseController {

    @Autowired
    private AdminService adminService;
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

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> login(@RequestBody UserLoginDTO userLoginDTO){
        Map<String, String> tokenMap = new HashMap<>();
        String token = null;
        try {
            token = adminService.login(userLoginDTO.getUsername(),userLoginDTO.getPassword());
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        tokenMap.put("token", token);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> registerAdmin(@RequestBody UserRegisterAdminDTO userRegisterAdminDTO){
        User user = null;
        try {
            user = adminService.register(userRegisterAdminDTO.ToUser(),userRegisterAdminDTO.getRoleType());
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(user);
    }

    @ApiOperation(value = "ADMIN")
    @RequestMapping(value = "/ADMIN", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Map<String,Object>> ADMIN(){
        return CommonResult.success("ADMIN");
    }

    @ApiOperation(value = "ROOT")
    @RequestMapping(value = "/ROOT", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ROOT')")
    public ResponseEntity<Map<String,Object>> ROOT(){
        return CommonResult.success("ROOT");
    }
    @ApiOperation(value = "SYSTEM")
    @RequestMapping(value = "/SYSTEM", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('SYSTEM')")
    public ResponseEntity<Map<String,Object>> SYSTEM(){
        return CommonResult.success("SYSTEM");
    }
    @ApiOperation(value = "USER")
    @RequestMapping(value = "/USER", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<String,Object>> USER(){
        return CommonResult.success("USER");
    }
}
