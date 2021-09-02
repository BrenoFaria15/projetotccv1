package com.projeto.tccv1.service;

import com.projeto.tccv1.model.entity.Usuario;

public interface UsuarioService {
	Usuario autenticar(String usuario, String senha);
	
	Usuario novoUsuario(Usuario usuario);
	
	void validarUsuario(String usuario);

}
