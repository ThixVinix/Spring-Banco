package br.com.proway.springbanco.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.proway.springbanco.model.Cliente;
import br.com.proway.springbanco.model.Conta;

public class ClienteDTO {

	private Long id;

	/**
	 * Nome do cliente
	 */
	private String nome;

	/**
	 * CPF do cliente
	 */
	private String cpf;

	/**
	 * RG do cliente.
	 */
	private String rg;

	/**
	 * Endereco do cliente
	 */
	private String endereco;

	private ContaDTO contaDto;

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.rg = cliente.getRg();
		this.endereco = cliente.getEndereco();
		this.contaDto = new ContaDTO(cliente.getConta());
	}

	public ContaDTO getContaDto() {
		return contaDto;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public static Optional<List<ClienteDTO>> converter(List<Cliente> clientes) {
		if (clientes == null || clientes.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(clientes.stream().map(ClienteDTO::new).collect(Collectors.toList())); // .map(cliente -> new
																									// ClienteDTO(cliente))
		}
	}

}
