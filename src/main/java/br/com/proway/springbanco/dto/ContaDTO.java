package br.com.proway.springbanco.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.proway.springbanco.model.Conta;

public class ContaDTO {

	/**
	 * Numero da conta liberado pelo sistema.
	 */
	private String numero;

	/**
	 * Saldo da conta.
	 */
	private Double saldo;

	/**
	 * Limite da conta liberado pelo gerente.
	 */
	private Double limite;

	public ContaDTO(Conta conta) {
		this.numero = conta.getNumero();
		this.saldo = conta.getSaldo();
		this.limite = conta.getLimite();
	}

	public String getNumero() {
		return numero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public Double getLimite() {
		return limite;
	}

	/**
	 * <p>
	 * Converte uma lista de objetos "Conta" para uma lista "ContaDTO"
	 * </p>
	 * 
	 * @return {@link Optional}<{@link List}<{@link ContaDTO}>>
	 */
	public static Optional<List<ContaDTO>> converter(List<Conta> contas) {
		if (contas == null || contas.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(contas.stream().map(ContaDTO::new).collect(Collectors.toList())); // .map(conta -> new
																									// ContaDTO(conta))
		}
	}

	/**
	 * <p>
	 * Converte o objeto "Conta" para "ContaDTO"
	 * </p>
	 * 
	 * @return {@link Optional}<{@link ContaDTO}>
	 */
	public static Optional<ContaDTO> converter(Conta conta) {
		if (conta == null) {
			return Optional.empty();
		} else {
			return Optional.of(new ContaDTO(conta));
		}
	}

}
