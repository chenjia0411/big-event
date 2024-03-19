/*
package com.heima;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @Auther: chenjia
 * @Date: 2023/11/23 - 11 - 23 - 17:02
 * @Description: com.heima
 * @version: 1.0
 *//*

public class JwtTest {

    @Test
    public void testGen(){
        Map<String,Object> claims =new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        //生成jwt代码
        String tokeng=JWT.create()
                .withClaim("user",claims) //添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//添加过期时间
                .sign(Algorithm.HMAC256("itheima"));//指定算法，配置密钥
        System.out.println(tokeng);
    }

*/
/*    @Test
    public void testParse(){
        //定义字符串，模拟用户传递过来的token
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MDA3NzQxMTJ9." +
                "ZiE8HgOwCXSBiFstRnmXghgzPF6K0UTyZOTFnRo1tik";
        JWTVerifier jwtVerifier =  JWT.require(Algorithm.HMAC256("itheima")).build();//验证器

        DecodedJWT decodedJWT = jwtVerifier.verify(token);//解析token，生成解析后的JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();//得到所有载荷
        System.out.println(claims.get("user"));

    }*//*

}
*/
