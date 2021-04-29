package br.com.laet.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.laet.forum.modelo.Usuario;
import br.com.laet.forum.repository.UsuarioRepository;

@Service
public class AutenticadorService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		throw new UsernameNotFoundException("Usuario não encontrado");
	}

}
