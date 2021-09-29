package com.projeto.tccv1.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tccv1.model.entity.TipoAtendimento;



public interface TipoAtendimentoRepository extends JpaRepository<TipoAtendimento,Long> {
	boolean existsBytipoNome(String tipoNome);

}
