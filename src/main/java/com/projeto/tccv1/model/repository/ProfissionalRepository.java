package com.projeto.tccv1.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.tccv1.model.entity.Profissional;

public interface ProfissionalRepository  extends JpaRepository<Profissional,Long> {

}
