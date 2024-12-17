package br.com.lincadinho.lincadinho.service;

import br.com.lincadinho.lincadinho.dto.AtualizarUsuarioDTO;
import br.com.lincadinho.lincadinho.model.usuario.Usuario;
import br.com.lincadinho.lincadinho.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AWSService awsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }

    public Usuario buscarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new EntityNotFoundException("Usuário não encontrado para o ID:" + id);
        }

    }

    public Usuario atualizarDadosUsuario(AtualizarUsuarioDTO atualizarUsuarioDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(atualizarUsuarioDTO.id());
        if (usuario.isPresent()){
            Usuario usuarioAtualizar = usuario.get();
            if (atualizarUsuarioDTO.nome() != null) usuarioAtualizar.setNome(atualizarUsuarioDTO.nome());
            if (atualizarUsuarioDTO.fotoPerfil() != null) {
                if (usuarioAtualizar.getFoto_url() != null) {
                    awsService.deletarImagem(usuarioAtualizar.getFoto_url());
                }
                String nova_url = awsService.uploadImagem(atualizarUsuarioDTO.fotoPerfil());
                usuarioAtualizar.setFoto_url(nova_url);
            }
            return usuarioAtualizar;
        } else {
            throw new EntityNotFoundException("Usuário não encontrado para o ID:" + atualizarUsuarioDTO.id());
        }
    }
}
