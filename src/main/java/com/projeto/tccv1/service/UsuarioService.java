package com.projeto.tccv1.service;

import com.projeto.tccv1.model.entity.Usuario;

public interface UsuarioService {
	Usuario autenticar(String email, String senha);
	
	Usuario novoUsuario(Usuario usuario);
	
	void validarEmail(String email);

}
