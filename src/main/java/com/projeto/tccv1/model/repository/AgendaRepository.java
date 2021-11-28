package com.projeto.tccv1.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tccv1.model.entity.Agenda;


public interface AgendaRepository extends JpaRepository<Agenda,Long>{
	
	List<Agenda>findByData(LocalDate data);

}
