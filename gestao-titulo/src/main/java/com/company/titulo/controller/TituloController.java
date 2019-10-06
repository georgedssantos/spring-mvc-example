package com.company.titulo.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.titulo.model.business.TituloRN;
import com.company.titulo.model.dto.TituloDto;
import com.company.titulo.model.entity.Titulo;
import com.company.titulo.model.enums.StatusTitulo;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String PESQUISAR_TITULO_VIEW = "pages/pesquisarTitulos";
	private static final String CADASTRAR_TITULO_VIEW = "pages/cadastrarTitulo";
	
	@Autowired
	private TituloRN tituloRN;
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<TituloDto> obterTitulos = this.tituloRN.obterTitulos();
		
		ModelAndView mv = new ModelAndView(PESQUISAR_TITULO_VIEW);
		mv.addObject("titulos", obterTitulos);
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRAR_TITULO_VIEW);
		mv.addObject("tituloDto", new TituloDto());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated TituloDto tituloDto, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return CADASTRAR_TITULO_VIEW;
		}

		this.tituloRN.salvar(tituloDto);
		
		attributes.addFlashAttribute("mensagem", tituloDto.getCodigo()==null ? "Título salvo com sucesso!" : "Título alterado com sucesso!");
		return "redirect:/titulos/novo"; 
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		TituloDto tituloDto = this.tituloRN.popularTituloDto(titulo);
		ModelAndView mv = new ModelAndView(CADASTRAR_TITULO_VIEW); 
		mv.addObject("tituloDto", tituloDto);
		return mv;
	}
		
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
