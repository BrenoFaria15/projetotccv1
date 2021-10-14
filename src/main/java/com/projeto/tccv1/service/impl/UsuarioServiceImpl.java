package com.projeto.tccv1.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tccv1.exeception.ErroAutenticacao;
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
	public Usuario autenticar(String usuario, String senha) {
		Optional<Usuario> usuarioRepositorio =repository.findByNome(usuario);
		
		if(!usuarioRepositorio.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado");
		}
		if(!usuarioRepositorio.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha Invalida");
		}
		return usuarioRepositorio.get();
	}

	@Override
	@Transactional
	public Usuario novoUsuario(Usuario usuario) {
		validarUsuario(usuario.getNome());
		return repository.save(usuario);
	}

	@Override
	public void validarUsuario(String usuario) {
		boolean existe=repository.existsByNome(usuario);
		if(existe) {
			throw new RegraNegocioException("Ja existe um usuário com esse nome cadastrado");
		}
		
	}

	@Override
	public Optional<Usuario> buscarPorId(Long idUsuario) {
		
		return repository.findById(idUsuario);
	}

	

}
