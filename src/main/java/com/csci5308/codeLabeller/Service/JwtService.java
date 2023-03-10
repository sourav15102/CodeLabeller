package com.csci5308.codeLabeller.Service;

import com.csci5308.codeLabeller.Enums.JwtEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private String secretStringKey = "2A472D4B6150645367566B58703273357638792F423F4528482B4D6251655468";
    private Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretStringKey));
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(JwtEnum.Authority.toString(), userDetails.getAuthorities());
        return encodeTheUserWithClaims(userDetails, claimsMap);
    }
    private String encodeTheUserWithClaims(UserDetails userDetails, Map<String, Object> claimsMap) {
        String token = Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + hoursToMiliseconds(10)))
                .signWith(secretKey, SignatureAlgorithm.HS256).compact();

        return token;
    }
    private long hoursToMiliseconds(int hour) {
        int milisecond = hour * 60 * 60 * 1000;
        return milisecond;
    }

    public String getUsername(String jwtToken) {
        Claims claims = getAllClaims(jwtToken);
        return claims.getSubject();
    }

    public Date getEpirationDate(String jwtToken) {
        return getAllClaims(jwtToken).getExpiration();
    }

    public boolean isValid(String jwtToken, UserDetails userDetails) {
        String username = getUsername(jwtToken);
        if(!isExpired(jwtToken) && userDetails.getUsername().equals(username)) {
            return true;
        }
        return false;
    }

    private boolean isExpired(String jwtToken) {
        if(getEpirationDate(jwtToken).before(new Date())){
            return true;
        }
        return false;
    }

    private Claims getAllClaims(String jwtToken){
         return (Claims)Jwts.parserBuilder().setSigningKey(secretKey).build().parse(jwtToken).getBody();
    }
}
