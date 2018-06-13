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
import org.forit.blog.dao.UtenteDAO;
import org.forit.blog.dto.UtenteDTO;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Casper
 */
public class Authentication {

    private Key keyProperty;
    private String encriptionKey = "EncryptionKeyDenis";

    public String getJWS(String subjectUrl, String email, String authScope) throws BlogException {
        try {
            return Jwts.builder()
                    .setSubject(subjectUrl)
                    // .setExpiration(new Date((long)Math.floor(new Date().getTime()/1000) + 7*24*60*60))
                    .claim("name", email)
                    .claim("scope", authScope)
                    .signWith(
                            SignatureAlgorithm.HS256,
                            encriptionKey.getBytes("UTF-8")
                    )
                    .compact();
        } catch (UnsupportedEncodingException ex) {
            throw new BlogException(ex);
        }
    }

    public boolean checkJWSAdmin(String compactJws) throws BlogException {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(encriptionKey.getBytes("UTF-8"))
                    .parseClaimsJws(compactJws);
            String scope = (String) claims.getBody().get("scope");
            String email = (String) claims.getBody().get("name");
            UtenteDAO uDAO = new UtenteDAO();
            if (email != null) {
                UtenteDTO uDTO = uDAO.loadUtenteDTOByEmail(email);
                if (uDTO.getIsActive() == true && uDTO.getIsBanned() == false) {
                    return scope.equals("administrator");
                } else {
                    return false;
                }
            }
            return false;
        } catch (ExpiredJwtException | MalformedJwtException | io.jsonwebtoken.SignatureException | UnsupportedJwtException | IllegalArgumentException | UnsupportedEncodingException ex) {
            throw new BlogException(ex);
        }
    }

    public boolean checkJWSUtenteOrAdmin(String compactJws) throws BlogException {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(encriptionKey.getBytes("UTF-8"))
                    .parseClaimsJws(compactJws);
            String scope = (String) claims.getBody().get("scope");
            String email = (String) claims.getBody().get("name");
            UtenteDAO uDAO = new UtenteDAO();
            UtenteDTO uDTO = uDAO.loadUtenteDTOByEmail(email);
            if (email != null) {
                if (uDTO.getIsActive() == true && uDTO.getIsBanned() == false) {
                    return scope.equals("administrator") || scope.equals("utente");
                } else {
                    return false;
                }
            }
            return false;

        } catch (ExpiredJwtException | MalformedJwtException | io.jsonwebtoken.SignatureException | UnsupportedJwtException | IllegalArgumentException | UnsupportedEncodingException ex) {
            throw new BlogException(ex);
        }
    }

    public String getEmailFromJWS(String compactJws) throws BlogException {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(encriptionKey.getBytes("UTF-8"))
                    .parseClaimsJws(compactJws);
            return (String) claims.getBody().get("name");

        } catch (ExpiredJwtException | MalformedJwtException | io.jsonwebtoken.SignatureException | UnsupportedJwtException | IllegalArgumentException | UnsupportedEncodingException ex) {
            throw new BlogException(ex);
        }
    }
    
}
