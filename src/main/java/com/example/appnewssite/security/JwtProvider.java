package com.example.appnewssite.security;

import com.example.appnewssite.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    static final String secret = "letNoOneKnowTheSecretWork1011";
    static final long expireTime = 36_000_000;
    /**
     *
     * @param username username orqali token yasaymiz. <br>
     * Username cleant tomonidan keladi cleantni username
     * @return tokenni qaytaramiz
     */
    static public String generateToken(String username, Role roles){
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        String compact = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles",roles.toString())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return compact;
    }



    /**
     *
     * @param token KELADI JwtFilterdan yuboramiz
     * @return TOKENNI VALEDATSIYADAN OTQAZDIK (TOKEN BUZULMAGANLIGINI, MUDDAT OTMAGANLIGINI NA H.K) true or folse qaytadi
     */

     public boolean validateToken(String token){
         try{
             Jwts
                     .parser()
                     .setSigningKey(secret)
                     .parseClaimsJws(token);
             return true;
         }catch (Exception e){
          e.printStackTrace();
         }
         return false;
     }



    /**
     *
     * @param token keladi JwtFilterdan yuboramiz
     * @return Token ichidan username olamiz va username qaytadi
     */

    public String getUserNameFromToken(String token){
        try{
            String username = Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return username;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
