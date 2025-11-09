package pe.edu.utp.bodega_rb_api.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration}")
  private long jwtExpiration;

  public String generateToken(Authentication auth) {
    return Jwts.builder()
        .subject(auth.getName())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .signWith(getSignInKey())
        .compact();
  }

  public boolean validToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(getSignInKey())
          .build()
          .parseSignedClaims(token);

      return true;
    } catch (Exception e) {
      return false;
    }

  }

  public String extractUsername(String token) {
    return getClaims(token).getSubject();
  }

  public boolean isTokenExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }

  public Claims getClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSignInKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private SecretKey getSignInKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  public long getJwtExpiration() {
    return jwtExpiration;
  }
}
