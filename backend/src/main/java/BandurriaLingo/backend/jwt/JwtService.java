/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BandurriaLingo.backend.jwt;

import java.security.Key;

import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;


@Service
public class JwtService {

    private final String SECRET_KEY = "aHR0cDovL2NsaWVudGtleWJhbmR1cnJpYWxpbmdvYXBpLnNlY3JldF9rZXk=";

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date()) 
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) 
                .signWith(getKey());
                extraClaims.forEach(jwtBuilder::claim);

        return jwtBuilder.compact();
    }
    

    private Key getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
