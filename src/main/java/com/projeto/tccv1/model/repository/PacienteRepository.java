package com.projeto.tccv1.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tccv1.model.entity.Paciente;

public interface PacienteRepository  extends JpaRepository<Paciente,Long>{

	boolean existsByCpf(String cpf);
	boolean existsByRg(String rg);
	boolean existsByCns(String cns);
	Optional<Paciente> findByCpf(String cpf);
	Optional<Paciente> findByCns(String cns);
	List<Paciente>findByFlgAtivoTrueAndFlgObitoFalse();
}
