package br.com.alura.forum.config.security;

import br.com.alura.forum.controller.dto.TokenTipo;
import br.com.alura.forum.service.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;
import static java.util.regex.Pattern.quote;

public class ForumAuthenticationFilter extends OncePerRequestFilter {

    private final ForumTokenService forumTokenService;
    private final UsuarioService usuarioService;

    public ForumAuthenticationFilter(ForumTokenService forumTokenService, UsuarioService usuarioService) {
        this.forumTokenService = forumTokenService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final var token = getToken(request);
        final var isTokenValid = this.forumTokenService.validate(token);

        if (isTokenValid) {
            authenticUser(token);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        final var token = request.getHeader("Authorization");
        if (nonNull(token) && token.startsWith(TokenTipo.BEARER.name())) {
            return token.split(quote(" "))[1];
        }
        return null;
    }

    private void authenticUser(final String token) {
        final var userId = forumTokenService.getUserId(token);
        final var usuario = usuarioService.findById(userId);
        if (usuario.isPresent()) {
            final var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

}
