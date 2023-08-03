package com.chzu.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
/**
 * @author: lhy
 * jwt工具，用来生成、校验token以及提取token中的信息
 */
@Slf4j
public class JwtUtil {
    //指定一个token过期时间（毫秒）
    public static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;  //7天
    public static final String SECRET = "chzu";
    /**
     * 生成token
     */
    public static String getJwtToken(JwtUser user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);    //使用密钥进行哈希
        // 附带username信息的token
        return JWT.create()
                .withClaim("userId", user.getUserId())
                .withClaim("username", user.getUserName())
                .withClaim("fullName",user.getFullName())
                .withClaim("isAdmin",user.getIsAdmin())
                .withClaim("isInstitution",user.getIsInstitution())
                .withExpiresAt(date)  //过期时间
                .sign(algorithm);     //签名算法
    }

    /**
     * 校验token是否正确
     */
    public static boolean verifyToken(String token) {
        try {
            //根据密钥生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    // .withClaim("username", username)
                    .build();
            //效验TOKEN（其实也就是比较两个token是否相同）
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 在token中获取到username信息
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static JwtUser recreateUserFromToken(String token) {
        JwtUser user = new JwtUser();
        DecodedJWT jwt = JWT.decode(token);
        user.setUserId(jwt.getClaim("userId").asInt());
        user.setUserName(jwt.getClaim("username").asString());
        user.setFullName(jwt.getClaim("fullName").asString());
        user.setIsAdmin(jwt.getClaim("isAdmin").asBoolean());
        user.setIsInstitution(jwt.getClaim("isInstitution").asBoolean());
        //r-p映射在运行时去取
        return user;
    }

    /**
     * 判断是否过期
     */
    public static boolean isExpire(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().getTime() < System.currentTimeMillis() ;
    }
}
