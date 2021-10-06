package br.com.proway.springbanco.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.proway.springbanco.model.Cliente;

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
	private EnderecoDTO enderecoDto;

	private ContaDTO contaDto;

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.rg = cliente.getRg();
		this.enderecoDto = new EnderecoDTO(cliente.getEndereco());
		this.contaDto = new ContaDTO(cliente.getConta());
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

	public ContaDTO getContaDto() {
		return contaDto;
	}

	public EnderecoDTO getEnderecoDto() {
		return enderecoDto;
	}

	/**
	 * <p>
	 * Converte uma lista de objetos "Cliente" para uma lista "ClienteDTO"
	 * </p>
	 * 
	 * @return {@link Optional}<{@link List}<{@link ClienteDTO}>>
	 */
	public static Optional<List<ClienteDTO>> converter(List<Cliente> clientes) {
		if (clientes == null || clientes.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(clientes.stream().map(ClienteDTO::new).collect(Collectors.toList())); // .map(cliente ->
																										// new
																										// ClienteDTO(cliente))
		}
	}

	/**
	 * <p>
	 * Converte o objeto "Cliente" para "ClienteDTO"
	 * </p>
	 * 
	 * @return {@link Optional}<{@link ClienteDTO}>
	 */
	public static Optional<ClienteDTO> converter(Cliente cliente) {
		if (cliente == null) {
			return Optional.empty();
		} else {
			return Optional.of(new ClienteDTO(cliente));
		}
	}

}
