package com.company.titulo.model.entity;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.company.titulo.model.enums.StatusTitulo;

@Entity
public class Titulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo indStatus;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public StatusTitulo getIndStatus() {
		return indStatus;
	}
	public void setIndStatus(StatusTitulo indStatus) {
		this.indStatus = indStatus;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo, dataVencimento, descricao, indStatus, valor);
	}
	@Override
	public boolean equals(Object obj) {		
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Titulo)) {
			return false;
		}
		Titulo other = (Titulo) obj;
		return Objects.equals(codigo, other.codigo);
	}
}