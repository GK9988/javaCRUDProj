package com.garvk.CrudProj.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private String SECRET_KEY;

    public JwtService(){
        SECRET_KEY = generateSecretKey();
    }

    public String generateSecretKey(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            System.out.println("Secret Key: " + secretKey.toString());
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String aInUsername) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(aInUsername)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*3))
                .signWith(getKey()).compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String aInToken){
        return extractClaim(aInToken, Claims::getSubject);
    }

    private <T> T extractClaim(String aInToken, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(aInToken);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String aInToken) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(aInToken).getPayload();
    }

    public boolean validateToken(String aInToken, UserDetails userDetails) {
        final String userName = extractUserName(aInToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(aInToken));
    }

    private boolean isTokenExpired(String aInToken){
        return extractExpiration(aInToken).before(new Date());
    }

    private Date extractExpiration(String aInToken){
        return extractClaim(aInToken, Claims::getExpiration);
    }
}
