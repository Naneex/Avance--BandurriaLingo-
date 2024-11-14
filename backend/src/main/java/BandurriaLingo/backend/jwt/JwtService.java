/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BandurriaLingo.backend.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


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

    public String getUsuarioFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String usuario = getUsuarioFromToken(token);
        return (usuario.equals(userDetails.getUsername()) && !isTokenExpired(token));
        
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration (String token){
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

   
}



