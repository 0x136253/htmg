package com.zstu.htmg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Anonsmd
 * @Date: 2020/6/27 0:08
 */
public class NormalTest {
    private static String reg = "\\[((.|\\n)*)\\]";
    private static Pattern pattern = Pattern.compile(reg);

    public static void main(String[] args) {
        String str = "{userLoginDTO=com.zstu.htmg.dto.UserLoginDTO@35e70db2[\n" +
                "  username=Anon\n" +
                "  password=string\n" +
                "]}";
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()){
            System.out.println(matcher.group(0));
        }
//        while (matcher.find()){
//            System.out.println(matcher.group());
//        }

    }
}
