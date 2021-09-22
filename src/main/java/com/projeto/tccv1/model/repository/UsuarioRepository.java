package com.projeto.tccv1.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.projeto.tccv1.model.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	boolean existsByNome(String nome);

	Optional<Usuario> findByNome(String nome);
}
