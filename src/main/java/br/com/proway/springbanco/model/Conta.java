package br.com.proway.springbanco.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.proway.springbanco.converter.TipoContaConverter;
import br.com.proway.springbanco.enums.TipoContaEnum;

@Entity
@Table(name = "contas")
public class Conta {

	@Transient
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Numero da conta liberado pelo sistema.
	 */
	@Column(name = "numero_conta", columnDefinition = "VARCHAR", unique = true, updatable = false, nullable = false)
	private String numero;

	/**
	 * Senha da conta liberado pelo sistema.
	 */
	@Column(name = "senha", columnDefinition = "SMALLINT", unique = false, updatable = true, nullable = false)
	private Integer senha;

	/**
	 * Saldo da conta.
	 */
	@Column(name = "saldo", columnDefinition = "DECIMAL", unique = false, updatable = true, nullable = false)
	private Double saldo;

	/**
	 * Limite da conta liberado pelo gerente.
	 */
	@Column(name = "limite", columnDefinition = "DECIMAL", unique = false, updatable = true, nullable = false)
	private Double limite;

	@Convert(converter = TipoContaConverter.class)
	@Column(name = "tipo", columnDefinition = "CHAR", unique = false, updatable = true, nullable = false)
	private Enum<TipoContaEnum> tipoConta;

	@Basic
	@Column(name = "data_abertura", columnDefinition = "DATE", unique = false, updatable = false, nullable = false)
	private LocalDate dataAbertura;

	/**
	 * <p>
	 * Construtor para contas especiais, nas quais o limite eh personalizado.
	 * </p>
	 * 
	 * @param numero Numero da conta.
	 * @param senha  Senha da conta.
	 * @param limite Limite da conta.
	 */
	public Conta(String numero, Integer senha, Double limite) {
		super();
		setNumero(numero);
		setSenha(senha);
		setSaldo(0d);
		setLimite(limite);
		setTipoConta(TipoContaEnum.ESPECIAL);
		setDataAbertura(LocalDate.now());
	}

	/**
	 * <p>
	 * Construtor para contas normais(comuns), nas quais o limite padrao da conta
	 * inicialmente eh definido para "500".
	 * </p>
	 * 
	 * @param numero Numero da conta.
	 * @param senha  Senha da conta.
	 */
	public Conta(String numero, Integer senha) {
		super();
		setNumero(numero);
		setSenha(senha);
		setSaldo(0d);
		setLimite(500d);
		setTipoConta(TipoContaEnum.NORMAL);
		setDataAbertura(LocalDate.now());
	}

	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb.append("\n\tNumero da conta: " + getNumero());
		sb.append("\n\tSenha: ");

		for (var i = 0; i < getSenha().toString().length(); i++)
			sb.append("*");

		sb.append("\n\tTipo da conta: " + getTipoConta().toString());

		sb.append("\n\tData de abertura: " + getDataAberturaFormatada());

		sb.append("\n\t" + visualizarSaldoAtual());
		sb.append("\t" + visualizarLimiteAtual());

		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	private Integer getSenha() {
		return senha;
	}

	public void setSenha(Integer senha) {
		this.senha = senha;
	}

	public Double getSaldo() {
		return saldo;
	}

	private void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Double getLimite() {
		return limite;
	}

	private void setLimite(Double limite) {
		this.limite = limite;
	}

	public Enum<TipoContaEnum> getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(Enum<TipoContaEnum> tipo) {
		this.tipoConta = tipo;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	/**
	 * Retorna uma String da data de abertura da conta no seguinte formato:
	 * "dd/MM/yyyy"
	 */
	public String getDataAberturaFormatada() {
		
		if (getDataAbertura() == null) {
			return "";
		}
		
		return getDataAbertura().format(this.formatter);
	}

	private void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String visualizarSaldoAtual() {
		return "Saldo atual: " + getSaldo() + "\n";
	}

	public String visualizarLimiteAtual() {
		return "Limite atual: " + getLimite() + "\n";
	}

	/**
	 * 
	 * <p>
	 * Realiza o deposito na conta
	 * </p>
	 * 
	 * @param valorDeposito Valor para deposito
	 * @throws IllegalArgumentException Caso o valor do deposito seja negativo ou
	 *                                  igual a 0(zero), eh lancada a excecao.
	 */
	public void depositar(double valorDeposito) throws IllegalArgumentException {
		if (valorDeposito <= 0) {
			throw new IllegalArgumentException(
					"valor para deposito invalido: " + valorDeposito + ". Digite um numero positivo.");
		}

		setSaldo(getSaldo() + valorDeposito);
	}

	/**
	 * 
	 * <p>
	 * Realiza o saque da conta
	 * </p>
	 * 
	 * @param valorSaque Valor para saque
	 * @throws IllegalArgumentException Caso o valor do saque seja negativo,
	 *                                  ultrapasse o limite ou saldo atual da conta,
	 *                                  eh lancada a excecao.
	 */
	public void sacar(double valorSaque) throws IllegalArgumentException {
		double saldoAtual = this.getSaldo();
		double limiteConta = this.getLimite();

		if (valorSaque <= 0) {
			throw new IllegalArgumentException(
					"Valor para saque invalido: " + valorSaque + ". Digite um numero positivo.");
		}

		if (valorSaque > limiteConta) {
			throw new IllegalArgumentException(
					"Limite insuficiente para sacar este valor: " + valorSaque + ". Limite disponivel: " + limiteConta);
		}

		if (valorSaque > saldoAtual) {
			throw new IllegalArgumentException(
					"Saldo insuficiente para sacar este valor: " + valorSaque + ". Saldo disponivel: " + saldoAtual);
		}

		setSaldo(getSaldo() - valorSaque);
	}

}
