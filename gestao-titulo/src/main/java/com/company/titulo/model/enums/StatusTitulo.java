package com.company.titulo.model.enums;

public enum StatusTitulo {
	
	PENDENTE("Pendente"),
	RECEBIDO("Recebido");
	
	private String descricao;
	
	StatusTitulo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
