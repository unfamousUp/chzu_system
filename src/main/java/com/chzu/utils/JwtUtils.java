package com.chzu.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @author linZT
 * @date 2022-4-1 14:39
 * JWT工具类
 */
public class JwtUtils {

    /**
     * 两个常量： 过期时间；秘钥
     */
    public static final long EXPIRE = 1000*60*60*24; // 1s*60*60*24 = 24h
    public static final String SIGNATURE = "admin";

    /**
     * 生成token字符串的方法
     * @param userId
     * @param username
     * @return
     */
    public static String getJwtToken(String userId,String username){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder.
                // 1.header
                setHeaderParam("typ", "JWT") // token类型
                .setHeaderParam("alg", "HS256") // 算法类型
                // 2.payload:设置载荷token主体信息，存储用户信息
                .claim("userId", userId)
                .claim("username", username)
                // 设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject("lin-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) // 过期时间
                // 设置token_id
                .setId(UUID.randomUUID().toString())
                // 3.signature:设置签名
                .signWith(SignatureAlgorithm.HS256, SIGNATURE)
                .compact(); // 拼接这三部分形成token
        return jwtToken;
    }

    /**
     * 解析token字符串获取clamis
     */
    public static Claims parseJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * 判断token是否存在与有效
     * @Param jwtToken
     */
    public static boolean checkToken(String jwtToken){
        if (StringUtils.isEmpty(jwtToken)){
            return false;
        }
        try{
            //验证token
            Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(jwtToken);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @Param request
     */
    public static boolean checkToken(HttpServletRequest request){
        try {
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)){
                return false;
            }
            Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(token);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @Param request
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return (String) body.get("id");
    }
}
