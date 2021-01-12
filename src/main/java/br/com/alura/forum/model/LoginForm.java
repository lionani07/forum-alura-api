package br.com.alura.forum.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder(toBuilder = true)
@Getter
@Setter
public class LoginForm {

    @NotBlank(message = "Email é campo obrigatório")
    @Email(message = "Email incorrecto")
    private final String email;

    @NotBlank(message = "Senha é campo obrigatório")
    private final String senha;

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }
}
