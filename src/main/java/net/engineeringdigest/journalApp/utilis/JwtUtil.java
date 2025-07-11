package net.engineeringdigest.journalApp.utilis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private String SECRET_KEY = "9f91e023b175a84177a5038ed2ba6046a444e8c89a60c7304cf436b81cb1453f";

    private SecretKey getSigninKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    public String extractClaim(String token, String claim){
        return extractAllClaims(token).getSubject();
    }


    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

private Boolean isTokenExpired(String token){
    return  extractExpiration(token).before(new Date());
}
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject){
    return Jwts.builder()
            .claims(claims)
            .subject(subject)
            .header().empty().add("typ", "JWT")
            .and()
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
            .signWith(getSigninKey())
            .compact();
    }

    public Boolean validateToken(String token){
        return  !isTokenExpired(token);
    }




}
