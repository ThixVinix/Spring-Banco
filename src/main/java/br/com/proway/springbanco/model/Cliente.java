package br.com.proway.springbanco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente implements Comparable<Cliente> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nome do cliente
	 */
	@Column(name = "nome", columnDefinition = "VARCHAR", unique = false, updatable = true, nullable = false)
	private String nome;

	/**
	 * CPF do cliente
	 */
	@Column(name = "cpf", columnDefinition = "VARCHAR", unique = true, updatable = false, nullable = false)
	private String cpf;

	/**
	 * RG do cliente.
	 */
	@Column(name = "rg", columnDefinition = "VARCHAR", unique = true, updatable = false, nullable = false)
	private String rg;

	/**
	 * Endereco do cliente
	 */
	@Column(name = "endereco", columnDefinition = "VARCHAR", unique = false, updatable = true, nullable = false)
	private String endereco;

	@OneToOne
	private Conta conta;

	/**
	 * Construtor de clientes
	 */
	public Cliente(Long id, String nome, String cpf, String rg, String endereco) {
		super();
		setId(id);
		setNome(nome);
		setCpf(cpf);
		setRg(rg);
		setEndereco(endereco);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		return result;
	}

	@Override
	public int compareTo(Cliente other) {
		return this.getNome().compareTo(other.getNome());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		return true;
	}

	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb.append("\n\tID: " + getId());
		sb.append("\n\tNome: " + getNome());
		sb.append("\n\tCPF: " + getCpf());
		sb.append("\n\tRG: " + getRg());
		sb.append("\n\tEndereço: " + getEndereco());
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void validarCpf() {

	}

}
