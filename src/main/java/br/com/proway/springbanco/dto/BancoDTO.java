package br.com.proway.springbanco.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.proway.springbanco.model.Banco;

public class BancoDTO {

	private Long id;
	private String nome;
	private String cnpj;
	private Integer agencia;

	public BancoDTO(Banco banco) {
		this.id = banco.getId();
		this.nome = banco.getNome();
		this.cnpj = banco.getCnpj();
		this.agencia = banco.getAgencia();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Integer getAgencia() {
		return agencia;
	}

	/**
	 * <p>
	 * Converte uma lista de objetos "Banco" para uma lista "BancoDTO"
	 * </p>
	 * 
	 * @return {@link Optional}<{@link List}<{@link BancoDTO}>>
	 */
	public static Optional<List<BancoDTO>> converter(List<Banco> bancos) {
		if (bancos == null || bancos.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(bancos.stream().map(BancoDTO::new).collect(Collectors.toList()));// .map(banco ->
																								// newBancoDTO(banco))
		}
	}

	/**
	 * <p>
	 * Converte o objeto "Banco" para "BancoDTO"
	 * </p>
	 * 
	 * @return {@link Optional}<{@link BancoDTO}>
	 */
	public static Optional<BancoDTO> converter(Banco banco) {
		if (banco == null) {
			return Optional.empty();
		} else {
			return Optional.of(new BancoDTO(banco));
		}
	}

}
