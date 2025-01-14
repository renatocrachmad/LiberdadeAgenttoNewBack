package br.com.liberdade.Agentto.security;

import br.com.liberdade.Agentto.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "suaChaveSecreta"; // Substitua por uma chave mais segura
    private static final long EXPIRATION_TIME = 86400000; // 1 dia em milissegundos

    /**
     * Gera um token JWT para o usuário fornecido.
     *
     * @param usuario o objeto do usuário
     * @return o token JWT gerado
     */
    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getLogin()) // Identifica o usuário pelo login
                .setIssuedAt(new Date()) // Data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Data de expiração
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // Algoritmo e chave secreta
                .compact();
    }
}
