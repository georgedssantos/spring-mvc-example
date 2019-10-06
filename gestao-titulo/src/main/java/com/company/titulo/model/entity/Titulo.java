package com.company.titulo.model.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.company.titulo.model.enums.StatusTitulo;

@Entity
@Table(name = "TITULO")
public class Titulo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4832158689079353624L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO", nullable = false)
	private Long codigo;
	
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_VENCIMENTO", nullable = false)
	private Date dataVencimento;
	
	@Column(name = "VALOR", nullable = false)
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "IND_STATUS", nullable = false)
	private StatusTitulo indStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRIADO_EM", nullable = false)
	private Date criadoEm;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ALTERADO_EM", nullable = true)
	private Date alteradoEm;
	
	
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
	public Date getCriadoEm() {
		return criadoEm;
	}
	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}
	public Date getAlteradoEm() {
		return alteradoEm;
	}
	public void setAlteradoEm(Date alteradoEm) {
		this.alteradoEm = alteradoEm;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo, dataVencimento, descricao, indStatus, valor, criadoEm, alteradoEm);
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