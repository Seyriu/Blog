/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.authentication;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import org.forit.blog.exceptions.BlogException;

/**
 *
 * @author Casper
 */
public class Authentication {

    private Key keyProperty;

    public String getJWS(String subject) {
// We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.
        keyProperty = MacProvider.generateKey();
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, keyProperty)
                .compact();
    }

    public void checkJWS(String compactJws, String subject) throws BlogException {
        try {
            boolean isTrustable = Jwts.parser().setSigningKey(keyProperty).parseClaimsJws(compactJws).getBody().getSubject().equals(subject); // Jwts.parser().setSigningKey(keyProperty).parseClaimsJws(compactJws);
            //OK, we can trust this JWT

        } catch (ExpiredJwtException | MalformedJwtException | io.jsonwebtoken.SignatureException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BlogException(ex);
            //don't trust the JWT!
        }
    }
    
    
//        static void testRsa(SignatureAlgorithm alg, int keySize=1024, boolean verifyWithPrivateKey=false) {
//
//        KeyPair kp = RsaProvider.generateKeyPair(keySize)
//        PublicKey publicKey = kp.getPublic();
//        PrivateKey privateKey = kp.getPrivate();
//
//        def claims = [iss: 'joe', exp: later(), 'http://example.com/is_root':true]
//
//        String jwt = Jwts.builder().setClaims(claims).signWith(alg, privateKey).compact();
//
//        def key = publicKey;
//        if (verifyWithPrivateKey) {
//            key = privateKey;
//        }
//
//        def token = Jwts.parser().setSigningKey(key).parse(jwt);
//
//        assert [alg: alg.name()] == token.header
//        //noinspection GrEqualsBetweenInconvertibleTypes
//        assert token.body == claims
//}
}
