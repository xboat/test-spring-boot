package com.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xboat date 2019-03-15
 */
public class JWTUtil {

    // 过期时间5分钟
    private static final long EXPIRES_AT = 5 * 60 * 1000;
    private static final String SECRET = "secret";
    private static final String ISSUSER = "auth0";


    public static String sign() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        Date nowDate = new Date();
        Date date = new Date(nowDate.getTime() + EXPIRES_AT);
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create()
                    .withHeader(map)
                    /* 签名生成者*/
                    .withIssuer(ISSUSER)
                    .withClaim("name", "xboat")
                    .withArrayClaim("array", new String[]{"张三","李四"})
                    .withAudience("API")
                    /* 签名的主题 */
                    .withSubject("test jwt")
                    /* 生成签名的时间 */
                    .withIssuedAt(nowDate)
                    /* 签名过期时间*/
                    .withExpiresAt(date)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            return null;
        }
    }

    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUSER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static JwtMap decoded(String token) {
        JwtMap map = new JwtMap();
        try {
            DecodedJWT jwt = JWT.decode(token);
            Map<String, Claim> claims = jwt.getClaims();
            map.put("name",claims.get("name").asString());
            map.put("array",claims.get("array").asArray(String.class));
            map.put("audience",jwt.getAudience());
            map.put("subject",jwt.getSubject());
            return map;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
