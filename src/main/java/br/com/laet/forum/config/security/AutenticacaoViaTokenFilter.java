package br.com.laet.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.laet.forum.modelo.Usuario;
import br.com.laet.forum.repository.UsuarioRepository;
import br.com.laet.forum.services.TokenService;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = pegaTokenDaRequisicao(request);
		boolean isTokenValido = tokenService.isTokenValido(token);
		if (isTokenValido) {
			autenticaUsuario(token);
		}
		filterChain.doFilter(request, response);
	}

	private void autenticaUsuario(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken dadosDeAutenticaoNoToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(dadosDeAutenticaoNoToken);
	}

	private String pegaTokenDaRequisicao(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken == null || bearerToken.isEmpty() || !bearerToken.startsWith("Bearer")) {
			return null;
		}
		return bearerToken.substring(7, bearerToken.length());
	}

}
