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
import java.time.LocalDateTime;
import java.util.Date;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Casper
 */
public class Authentication {

    private Key keyProperty;

    public String getJWS() throws BlogException {
        try {
            return Jwts.builder()
                    .setSubject("users/TzMUocMF4p")
                    // .setExpiration(new Date((long)Math.floor(new Date().getTime()/1000) + 7*24*60*60))
                    .claim("name", "Robert Token Man")
                    .claim("scope", "self groups/admins")
                    .signWith(
                            SignatureAlgorithm.HS256,
                            "secret".getBytes("UTF-8")
                    )
                    .compact();
        } catch (UnsupportedEncodingException ex) {
            throw new BlogException(ex);
        }
    }

    public void checkJWS(String compactJws, String subject) throws BlogException {
        try {
             Jws<Claims> claims = Jwts.parser()
                    .setSigningKey("secret".getBytes("UTF-8"))
                    .parseClaimsJws(compactJws);
            String scope = (String) claims.getBody().get("scope");
            Boolean b = scope.equals("self groups/admins");

        } catch (ExpiredJwtException | MalformedJwtException | io.jsonwebtoken.SignatureException | UnsupportedJwtException | IllegalArgumentException | UnsupportedEncodingException ex) {
            throw new BlogException(ex);
        }
    }

}
