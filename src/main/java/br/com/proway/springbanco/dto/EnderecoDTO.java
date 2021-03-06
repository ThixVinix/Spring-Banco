package br.com.proway.springbanco.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.proway.springbanco.model.Endereco;

public class EnderecoDTO {

	private String cep;

	private String logradouro;

	private String bairro;

	private String cidade;

	private String uf;

	public EnderecoDTO(Endereco endereco) {
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.uf = endereco.getUf();
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getUf() {
		return uf;
	}

	/**
	 * <p>
	 * Converte uma lista de objetos "Endereco" para uma lista "EnderecoDTO"
	 * </p>
	 * 
	 * @return {@link Optional}<{@link List}<{@link EnderecoDTO}>>
	 */
	public static Optional<List<EnderecoDTO>> converter(List<Endereco> enderecos) {
		if (enderecos == null || enderecos.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(enderecos.stream().map(EnderecoDTO::new).collect(Collectors.toList())); // .map(endereco
																										// -> new
																										// EnderecoDTO(endereco))
		}
	}

	/**
	 * <p>
	 * Converte o objeto "Endereco" para "EnderecoDTO"
	 * </p>
	 * 
	 * @return {@link Optional}<{@link EnderecoDTO}>
	 */
	public static Optional<EnderecoDTO> converter(Endereco endereco) {
		if (endereco == null) {
			return Optional.empty();
		} else {
			return Optional.of(new EnderecoDTO(endereco));
		}
	}

}
