package com.projeto.tccv1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Usuario;
import com.projeto.tccv1.model.repository.UsuarioRepository;
import com.projeto.tccv1.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;
	
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario novoUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String email) {
		boolean existe=repository.existByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Ja existe um usuário com esse email cadastrado");
		}
		
	}

}
