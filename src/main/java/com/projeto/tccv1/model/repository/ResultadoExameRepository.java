package com.projeto.tccv1.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.ResultadoExame;

public interface ResultadoExameRepository extends JpaRepository<ResultadoExame,Long> {
	
	List<ResultadoExame>findByPaciente(Paciente paciente);

}
