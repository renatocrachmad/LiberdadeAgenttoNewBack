package br.com.liberdade.Agentto.security;

import br.com.liberdade.Agentto.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "suaChaveSecreta"; 
    private static final long EXPIRATION_TIME = 86400000; // 1 dia em milissegundos

    
    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getLogin()) 
                .setIssuedAt(new Date()) // Data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Data de expiração
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) 
                .compact();
    }
}
