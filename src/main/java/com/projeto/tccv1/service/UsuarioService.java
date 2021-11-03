package com.projeto.tccv1.service;

import java.util.List;
import java.util.Optional;

import com.projeto.tccv1.model.entity.Usuario;

public interface UsuarioService {
	Usuario autenticar(String usuario, String senha);
	
	Usuario novoUsuario(Usuario usuario);
	
	void validarUsuario(String usuario);

	Optional<Usuario> buscarPorId(Long idUsuario);
	List<Usuario> buscarTodos();
	Usuario atualizar(Usuario usuario);
	void deletar(Usuario usuario);
}
