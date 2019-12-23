package com.company.titulo.controller;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.titulo.model.business.TituloRN;
import com.company.titulo.model.criteria.TituloCriteria;
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
    public String pesquisar(HttpServletRequest request, Model model, @ModelAttribute("tituloCriteria") TituloCriteria tituloCriteria) {
        
        int page = 0; //DEFAULT PAGE NUMBER IS 0 (YES IT IS WEIRD)
        int size = 5; //DEFAULT PAGE SIZE IS 5
        
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        
        model.addAttribute("titulos", this.tituloRN.pesquisarTitulosPage(tituloCriteria, page, size));
        
        return PESQUISAR_TITULO_VIEW;
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
		
		try {
			this.tituloRN.salvar(tituloDto);		
			attributes.addFlashAttribute("mensagem", tituloDto.getCodigo()==null ? "Título salvo com sucesso!" : "Título alterado com sucesso!");
			
			return tituloDto.getCodigo()==null ? "redirect:/titulos/novo" : "redirect:/titulos"; 
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRAR_TITULO_VIEW;
		}
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		TituloDto tituloDto = this.tituloRN.popularTituloDto(titulo);
		ModelAndView mv = new ModelAndView(CADASTRAR_TITULO_VIEW); 
		mv.addObject("tituloDto", tituloDto);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		this.tituloRN.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {
		return this.tituloRN.receber(codigo);
	}
		
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
