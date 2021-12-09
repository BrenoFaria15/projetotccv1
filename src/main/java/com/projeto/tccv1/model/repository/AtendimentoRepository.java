package com.projeto.tccv1.model.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tccv1.model.entity.Atendimento;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.Profissional;
import com.projeto.tccv1.model.entity.Unidade;

public interface AtendimentoRepository extends JpaRepository<Atendimento,Long> {
	
	List<Atendimento>findByPaciente(Paciente paciente);
	
	List<Atendimento>findByPacienteAndProfissionalAndDataAndUnidade(Paciente paciente,Profissional profissional,LocalDate data,Unidade unidade);

	
	List<Atendimento>findByPacienteAndDataAndUnidade(Paciente paciente,LocalDate data,Unidade unidade);
	List<Atendimento>findByDataAndUnidade(LocalDate data,Unidade unidade);
	List<Atendimento>findByProfissionalAndDataAndUnidade(Profissional profissional,LocalDate data,Unidade unidade);
	
}
