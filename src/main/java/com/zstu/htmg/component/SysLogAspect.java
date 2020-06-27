package com.zstu.htmg.component;

import com.zstu.htmg.api.MyLog;
import com.zstu.htmg.mapper.LogMapper;
import com.zstu.htmg.mapper.UserMapper;
import com.zstu.htmg.pojo.Log;
import com.zstu.htmg.util.IPUtils;
import com.zstu.htmg.util.JwtTokenUtil;
import com.zstu.htmg.util.ObjectUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/26 22:37
 */

@Aspect
@Component
public class SysLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
//    @Autowired
//    private ObjectUtils objectUtils;
    @Autowired
    private LogMapper logDAO;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    private String reg = "\\[((.|\\n|\\r)*)\\]";
    private Pattern pattern = Pattern.compile(reg);
    @Pointcut("@annotation( com.zstu.htmg.api.MyLog)")
    public void logPointCut(){
    }

    @AfterReturning("logPointCut()")
    public void saveSysLogs(JoinPoint joinPoint){
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>logs");
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>logs");
        Log sysLog = new Log();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String operation = myLog.operation();
            //保存获取的操作
            sysLog.setOperationname(operation);
            sysLog.setOperationtime(new Date());
            String database = myLog.database();
            //保存获取的操作
            sysLog.setDatabaseName(myLog.database());
        }

        //获取请求的类名
        String[] className = joinPoint.getTarget().getClass().getName().split("\\.");
        sysLog.setClassname(className[className.length-1]);
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(methodName);


        //保存所有请求参数，用于输出到日志中
        if (myLog.flag()){
            Map allParams = new HashMap();
            Object[] paramValues = joinPoint.getArgs();
            String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
            for(int i=0;i<paramNames.length;i++){
                if (paramValues[i] instanceof Map<?, ?>) {
                    //提取方法中的MAP参数，用于记录进日志中
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) paramValues[i];
                    allParams.putAll(map);
                }else {
                    allParams.put(paramNames[i], ToStringBuilder.reflectionToString(paramValues[i], ToStringStyle.MULTI_LINE_STYLE));
                 }
            }
            if (allParams.toString().length()<= 800){
//                String reg = "\\[((.|\\n|\\r)*)\\]";
//                Pattern pattern = Pattern.compile(reg);
                String str = allParams.toString();
                Matcher matcher = pattern.matcher(str);
                String answ = "";
                while (matcher.find()){
                    answ += matcher.group();
                }
                sysLog.setParams(answ);
            }
            else {
                sysLog.setParams("The Param is too long!");
            }
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取用户名
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null) {
            String authToken = authHeader;
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            List<String> userlist = userMapper.selectUserIDByUsername(username);
            if (userlist.size()>=1) {
                sysLog.setUserid(userlist.get(0));
            }
        }
        //获取用户ip地址
        try {
            sysLog.setIp(IPUtils.getIpAddress(request));
            sysLog.setIp(request.getRemoteAddr());
        } catch (IOException e) {
            e.printStackTrace();
        }
        sysLog.setIssuccess(true);
        logDAO.insertSelectiveWithoutID(sysLog);
        LOGGER.info("logs<<<<<<<<<<<<<<<<<<<<<<<");
//        System.out.println("logs<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
