package com.slippery.contriop.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final Long EXPIRATION_TIME =864000L;
    private final String JWT_GEN ="1dc56db6ce4ca3a15399dd6b32d372c43ebd83a9ec873a7edecfce8bd0f0fa6a7d6c2dca79aaede9be7576c3f3572457658edeb39452ffa009c6d75aa2dfc6cf4247d29d161a05d29ae73a4d93915d6b0966d965a2566847c1b87faf5304d7b683c5d71c0dd0648b07e7c1e84e0bbabcd175f6bf2138874b05b38c002f2e1637182a0e47dc4432d67c0fa8b29889260f7066447e7d1305f820fbf739964c9256b563a047fdc6da482ce2ecb68cb3e29dae971288b2ee0d9f204747e01b6d6ba8861daf4554098a5f4f4daefd3cf11c7e63f77bae0ceeca1b917e2e33210666ef6921ac15632c569913bb549240e660333931608ef2aeb634c52a6e1aa0bcd9d9";

    private SecretKey generateToken(){
        byte[] keyBytes = Base64.getDecoder().decode(JWT_GEN);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateJwtToken(String username){
        Map<String,Object> claims =new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .and()
                .signWith(generateToken())
                .compact();
    }
}
