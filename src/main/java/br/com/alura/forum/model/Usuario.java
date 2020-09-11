package br.com.alura.forum.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(of = "id")
public class Usuario {

	private Long id;
	private String nome;
	private String email;
	private String senha;
}
