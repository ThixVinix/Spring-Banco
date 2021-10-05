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

	/**
	 * <p>
	 * Construtor para contas especiais, nas quais o limite eh personalizado.
	 * </p>
	 * 
	 * @param numero Numero da conta.
	 * @param senha  Senha da conta.
	 * @param limite Limite da conta.
	 */
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

	public static Optional<List<ContaDTO>> converter(List<Conta> contas) {
		if (contas == null || contas.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(contas.stream().map(ContaDTO::new).collect(Collectors.toList())); // .map(conta -> new
																									// ContaDTO(conta))
		}
	}

}
