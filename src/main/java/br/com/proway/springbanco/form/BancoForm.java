package br.com.proway.springbanco.form;

import java.util.InputMismatchException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.proway.springbanco.model.Banco;

public class BancoForm {

	@NotNull
	@NotBlank
	private String nome;

	@NotNull
	@NotBlank
	private String cnpj;

	@NotNull
	private Integer agencia;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Banco converter() throws InputMismatchException {

		if (getNome() == null || getNome().trim().equals(""))
			throw new InputMismatchException("Erro ao inserir usuário. (nome = " + getNome() + ")");

		if (getCnpj() == null || getCnpj().trim().equals(""))
			throw new InputMismatchException("Erro ao inserir usuário. (cnpj = " + getCnpj() + ")");

		if (getAgencia() == null || String.valueOf(getAgencia()).trim().equals(""))
			throw new InputMismatchException("Erro ao inserir usuário. (tipo = " + getAgencia() + ")");

		return new Banco(getNome(), getCnpj(), getAgencia());

	}
}
