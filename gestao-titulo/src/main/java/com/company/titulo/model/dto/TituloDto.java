package com.company.titulo.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import com.company.titulo.model.enums.StatusTitulo;

@Component
public class TituloDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2183654441084661958L;
	
	private Long codigo;
	
	@NotEmpty(message = "Descrição é obrigatória.")
	@Size(max = 60, message = "A descrição não pode conter mais de 60 caracteres.")
	private String descricao;
	
	@NotNull(message = "Data de vencimento é obrigatória.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@NotNull(message = "Valor é obrigatório.")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que 0,01.")
	@DecimalMax(value = "9999999.99", message = "Valor não pode ser maior que 9.999.999,99.")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
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
		if (!(obj instanceof TituloDto)) {
			return false;
		}
		TituloDto other = (TituloDto) obj;
		return Objects.equals(codigo, other.codigo);
	}
}
