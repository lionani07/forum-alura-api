package br.com.alura.forum.controller;

import br.com.alura.forum.config.security.ForumTokenService;
import br.com.alura.forum.controller.dto.TokenDto;
import br.com.alura.forum.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.alura.forum.controller.dto.TokenTipo.BEARER;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ForumTokenService forumTokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginForm loginForm) {
        try {
            final var authentication = this.authenticationManager
                    .authenticate(loginForm.toAuthentication());
            final var token = forumTokenService.generate(authentication);
            final var tokenDto = TokenDto.of(token, BEARER);
            return ResponseEntity.ok(tokenDto);
        } catch(AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
