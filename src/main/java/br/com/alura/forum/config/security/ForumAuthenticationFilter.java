package br.com.alura.forum.config.security;

import br.com.alura.forum.controller.dto.TokenTipo;
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

    public ForumAuthenticationFilter(ForumTokenService forumTokenService) {
        this.forumTokenService = forumTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final var token = getToken(request);
        final var isValid = this.forumTokenService.validate(token);

        System.out.printf("Token %s is valid: %s%n", token, isValid);
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        final var token = request.getHeader("Authorization");
        if (nonNull(token) && token.startsWith(TokenTipo.BEARER.name())) {
            return token.split(quote(" "))[1];
        }
        return null;
    }


}
