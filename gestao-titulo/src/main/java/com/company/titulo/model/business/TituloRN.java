package com.company.titulo.model.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.titulo.model.dto.TituloDto;
import com.company.titulo.model.entity.Titulo;
import com.company.titulo.model.repository.TituloRepository;

@Service
public class TituloRN {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	public List<TituloDto> obterTitulos() {
		List<Titulo> titulos = this.tituloRepository.findAll();
		List<TituloDto>  titulosDto =new ArrayList<>();
		for (Titulo titulo : titulos) {
			TituloDto tituloDto = popularTituloDto(titulo);
			titulosDto.add(tituloDto);
		}
		return titulosDto;
	}
	
	public void salvar(TituloDto tituloDto) {
		Titulo titulo = new Titulo();
		titulo.setCodigo(tituloDto.getCodigo());
		titulo.setDescricao(tituloDto.getDescricao());
		titulo.setDataVencimento(tituloDto.getDataVencimento());
		titulo.setValor(tituloDto.getValor());
		titulo.setIndStatus(tituloDto.getIndStatus());
		titulo.setCriadoEm(new Date());
		titulo.setAlteradoEm(tituloDto.getCodigo()!=null ? new Date() : null);
		
		this.tituloRepository.save(titulo);
	}
	
	public TituloDto popularTituloDto(Titulo titulo) {
		TituloDto tituloDto = new TituloDto();
		tituloDto.setCodigo(titulo.getCodigo());
		tituloDto.setDescricao(titulo.getDescricao());
		tituloDto.setDataVencimento(titulo.getDataVencimento());
		tituloDto.setValor(titulo.getValor());
		tituloDto.setIndStatus(titulo.getIndStatus());
		return tituloDto;
	}
}