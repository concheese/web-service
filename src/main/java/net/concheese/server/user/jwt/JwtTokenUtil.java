package net.concheese.server.user.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.concheese.server.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

  @Value("${jwt.secret}")
  private String secretKey;

  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    claims.put("loginId", user.getLoginId());
    claims.put("name", user.getName());
    claims.put("email", user.getEmail());
    claims.put("nickname", user.getNickname());
    claims.put("role", user.getUserRole());

    return generateAccessToken(claims, user.getLoginId());
  }

  public String generateAccessToken(Map<String, Object> claims, String loginId) {
    long tokenPeriod = 5 * 60 * 60;
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(loginId)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + tokenPeriod * 1000))
        .signWith(SignatureAlgorithm.HS512, secretKey).compact();
  }

}