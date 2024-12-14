package br.com.lincadinho.lincadinho.service;

import br.com.lincadinho.lincadinho.dto.LoginDTO;
import br.com.lincadinho.lincadinho.dto.RegistrarUsuarioDTO;
import br.com.lincadinho.lincadinho.infra.exception.EmailJaExistenteException;
import br.com.lincadinho.lincadinho.infra.exception.UsuarioNaoEncontradoException;
import br.com.lincadinho.lincadinho.model.organizacao.Organizacao;
import br.com.lincadinho.lincadinho.model.usuario.Usuario;
import br.com.lincadinho.lincadinho.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrganizacaoService organizacaoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public Usuario registrar(RegistrarUsuarioDTO dados) {
        if (this.usuarioService.loadUserByUsername(dados.email()) != null) {
            throw new EmailJaExistenteException("Email já está em uso");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        Organizacao organizacao = null;
        if (dados.organizacao() != null) {
            organizacao = organizacaoService.buscarOrganizacao(dados.organizacao());
        }

        Usuario usuarioNovo = new Usuario(dados, senhaCriptografada, organizacao);
        return usuarioRepository.save(usuarioNovo);
    }

    public String login (LoginDTO dados) {
        var usuario = usuarioRepository.findByEmail(dados.email());

        if (usuario == null) throw new BadCredentialsException("Email ou senha inválidos");

        try {
            var usuarioSenha = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var autenticacao = this.authenticationManager.authenticate(usuarioSenha);
            var token = this.tokenService.gerarToken((Usuario) autenticacao.getPrincipal());
            return token;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Email ou senha inválidos.");
        }

    }
}
