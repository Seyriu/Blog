/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Casper
 */
public class Authentication {

  private Key keyProperty;

  public String getJWS(String subjectUrl, String name, String authScope) throws BlogException {
    try {
      return Jwts.builder()
              .setSubject(subjectUrl)
              // .setExpiration(new Date((long)Math.floor(new Date().getTime()/1000) + 7*24*60*60))
              .claim("name", name)
              .claim("scope", authScope)
              .signWith(
                      SignatureAlgorithm.HS256,
                      "EncryptionKeyDenis".getBytes("UTF-8")
              )
              .compact();
    } catch (UnsupportedEncodingException ex) {
      throw new BlogException(ex);
    }
  }

  public boolean checkJWSAdmin(String compactJws) throws BlogException {
    try {
      Jws<Claims> claims = Jwts.parser()
              .setSigningKey("EncryptionKeyDenis".getBytes("UTF-8"))
              .parseClaimsJws(compactJws);
      String scope = (String) claims.getBody().get("scope");
      return scope.equals("administrator");

    } catch (ExpiredJwtException | MalformedJwtException | io.jsonwebtoken.SignatureException | UnsupportedJwtException | IllegalArgumentException | UnsupportedEncodingException ex) {
      throw new BlogException(ex);
    }
  }

  public boolean checkJWSUtenteOrAdmin(String compactJws) throws BlogException {
    try {
      Jws<Claims> claims = Jwts.parser()
              .setSigningKey("EncryptionKeyDenis".getBytes("UTF-8"))
              .parseClaimsJws(compactJws);
      String scope = (String) claims.getBody().get("scope");
      return scope.equals("administrator") || scope.equals("utente");

    } catch (ExpiredJwtException | MalformedJwtException | io.jsonwebtoken.SignatureException | UnsupportedJwtException | IllegalArgumentException | UnsupportedEncodingException ex) {
      throw new BlogException(ex);
    }
  }

}
