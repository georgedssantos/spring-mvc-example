package com.company.titulo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.titulo.model.entity.Titulo;
import com.company.titulo.repository.ITituloRepository;


@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private ITituloRepository tituloRepository;
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo) {
		// TODO : SALVAR NO BANCO DE DADOS
		
		this.tituloRepository.save(titulo);
		
		return "CadastroTitulo";
	}

}
