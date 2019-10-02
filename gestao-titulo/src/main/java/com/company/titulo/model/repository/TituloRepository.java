package com.company.titulo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.titulo.model.entity.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
	
}
