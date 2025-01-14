package br.com.liberdade.Agentto.Service;

import br.com.liberdade.Agentto.models.Usuario;
import br.com.liberdade.Agentto.repository.UsuarioRepository;
import br.com.liberdade.Agentto.security.JwtTokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String autenticar(String login, String senha) {
        // Validação básica de entrada
        if (login == null || login.isEmpty() || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Login e senha são obrigatórios.");
        }

        // Buscar usuário por login
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(login);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        Usuario usuario = usuarioOpt.get();

        // Verificar senha
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta.");
        }

        // Gerar e retornar o token JWT
        return jwtTokenUtil.generateToken(usuario);
    }
}
