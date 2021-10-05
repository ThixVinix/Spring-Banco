package br.com.proway.springbanco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contas")
public class Conta {

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
	}

	/**
	 * <p>
	 * Construtor para contas comuns, nas quais o limite padrao da conta
	 * inicialmente eh definido para "500".
	 * </p>
	 * 
	 * @param numero Numero da conta.
	 * @param senha  Senha da conta.
	 * @param limite Limite da conta.
	 */
	public Conta(String numero, Integer senha) {
		super();
		setNumero(numero);
		setSenha(senha);
		setSaldo(0d);
		setLimite(500d);
	}


	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb.append("\n\tNumero da conta: " + getNumero());
		sb.append("\n\tSenha: ");

		for (var i = 0; i < getSenha().toString().length(); i++)
			sb.append("*");
		
		sb.append("\n\t" + visualizarSaldoAtual());
		sb.append("\t" + visualizarLimiteAtual());

		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
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
	 * @throws IllegalArgumentException Caso o valor do deposito seja negativo, eh
	 *                                  lancada a excecao.
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
