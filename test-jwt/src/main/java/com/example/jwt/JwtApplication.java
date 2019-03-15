package com.example.jwt;

/**
 * @author xboat date 2019-03-15
 */
public class JwtApplication {

    public static void main(String[] args) {
        System.out.println("<---start jwt---->");
        String token = JWTUtil.sign();
        System.out.println(String.format("token---->%s",token));
        System.out.println(String.format("verify---->%s",JWTUtil.verify(token)));
        JwtMap jwtMap = JWTUtil.decoded(token);
        System.out.println(String.format("decoded---->%s",jwtMap.get("name")));
    }
}
