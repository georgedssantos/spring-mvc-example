package com.company.titulo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.titulo.model.Titulo;


@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo) {
		// TODO : SALVAR NO BANCO DE DADOS
		
		System.out.println(">>>>>>>>>>>>> "+titulo.getDescricao());
		
		return "CadastroTitulo";
	}

}
