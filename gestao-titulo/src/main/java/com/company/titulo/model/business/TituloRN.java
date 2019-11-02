package com.company.titulo.model.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.company.titulo.model.dto.TituloDto;
import com.company.titulo.model.entity.Titulo;
import com.company.titulo.model.repository.TituloRepository;

@Service
public class TituloRN {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	public List<TituloDto> obterTitulosDto() {
		List<Titulo> titulos = this.tituloRepository.findAll();
		List<TituloDto>  titulosDto =new ArrayList<>();
		for (Titulo titulo : titulos) {
			TituloDto tituloDto = popularTituloDto(titulo);
			titulosDto.add(tituloDto);
		}
		return titulosDto;
	}
	
	public Page<Titulo> titulosPage(Integer page, Integer size) {
		Page<Titulo> titulos = this.tituloRepository.findAll(PageRequest.of(page, size));
		//final Page<TituloDto> pages = new PageImpl<>(titulosDto);
		return titulos;
	}
	
	public void salvar(TituloDto tituloDto) {
		
		try {
			Titulo titulo = new Titulo();
			titulo.setCodigo(tituloDto.getCodigo());
			titulo.setDescricao(tituloDto.getDescricao());
			titulo.setDataVencimento(tituloDto.getDataVencimento());
			titulo.setValor(tituloDto.getValor());
			titulo.setIndStatus(tituloDto.getIndStatus());
			titulo.setCriadoEm(new Date());
			titulo.setAlteradoEm(tituloDto.getCodigo()!=null ? new Date() : null);
			
			this.tituloRepository.save(titulo);
	
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválida.");
		}
	}
	
	public void excluir(Long codigo) {
		this.tituloRepository.deleteById(codigo);
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
