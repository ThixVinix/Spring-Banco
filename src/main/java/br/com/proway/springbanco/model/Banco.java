package br.com.proway.springbanco.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.proway.springbanco.excecoes.InsercaoClienteException;
import br.com.proway.springbanco.excecoes.InsercaoLimiteEspecialException;

@Entity
@Table(name = "bancos")
public class Banco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", columnDefinition = "VARCHAR", length = 120, unique = true, updatable = true, nullable = false)
	private String nome;

	@Column(name = "cnpj", columnDefinition = "VARCHAR", length = 18, unique = true, updatable = false, nullable = false)
	private String cnpj;

	@Column(name = "agencia", columnDefinition = "SMALLINT", unique = true, updatable = false, nullable = false)
	private Integer agencia;

	/**
	 * Lista de clientes do Banco.
	 */
	@ManyToMany(targetEntity = Banco.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(joinColumns = { @JoinColumn(name = "banco_id", table = "bancos") }, inverseJoinColumns = {
			@JoinColumn(name = "cliente_id", table = "clientes") })
	private List<Cliente> clientes;

	/**
	 * <p>
	 * Construtor de criacao do Banco (A lista de clientes é inicializada vazia).
	 * </p>
	 * 
	 * @param nome    - Nome do Banco (String);
	 * @param cnpj    - CNPJ do Banco (String);
	 * @param agencia - Número da agência do banco (Integer).
	 */
	public Banco(String nome, String cnpj, Integer agencia) {
		super();
		setNome(nome);
		setCnpj(cnpj);
		setAgencia(agencia);
		setClientes(new ArrayList<>());
	}

	@SuppressWarnings("unused")
	private Banco() {
	}

	public Long getId() {
		return id;
	}

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

	/**
	 * <p>
	 * Retorna a lista <strong>imutável</strong> e <strong>ordenada por ordem
	 * alfabética</strong> de clientes do Banco apenas para visualização.
	 * </p>
	 */
	public List<Cliente> getClientes() {
		Collections.sort(this.clientes);
		return Collections.unmodifiableList(this.clientes);
	}

	private void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * <p>
	 * Adiciona um cliente no Banco alvo. (A geração do número da conta e da senha
	 * do cliente são geradas automaticamente pelo sistema)
	 * </p>
	 * 
	 * @param cliente - Objeto do cliente que irá ser vinculado ao Banco.
	 * 
	 * 
	 * @throws InsercaoClienteException Caso exista alguma informação do cliente
	 *                                  faltando ou preenchida de forma errada, será
	 *                                  lançada a exceção.
	 * @throws NoSuchAlgorithmException Caso ocorra um erro na geração da agência
	 *                                  automática e/ou na geração da senha
	 *                                  automática, será lançada a exceção.
	 */
	public void adicionarCliente(Cliente cliente) throws InsercaoClienteException, NoSuchAlgorithmException {

		validarInformacoesCliente(cliente);

		var conta = new Conta(gerarNumeroAgenciaRandomico(), gerarSenha4DigitosRandomico());

		cliente.setConta(conta);

		this.clientes.add(cliente);

	}

	/**
	 * <p>
	 * Adiciona um cliente no Banco alvo. Também é possível determinar um limite
	 * especial para o cliente. (A geração do número da conta e da senha do cliente
	 * são geradas automaticamente pelo sistema)
	 * </p>
	 * 
	 * @param cliente        - Objeto do cliente que irá ser vinculado ao Banco.
	 * @param limiteEspecial - Limite especial que poderá ser definido pelo Gerente.
	 * 
	 * 
	 * @throws InsercaoClienteException        Caso exista alguma informação do
	 *                                         cliente faltando ou preenchida de
	 *                                         forma errada, será lançada a exceção.
	 * @throws InsercaoLimiteEspecialException Caso o limite especial seja menor ou
	 *                                         igual à "500", será lançada a
	 *                                         exceção.
	 * @throws NoSuchAlgorithmException        Caso ocorra um erro na geração da
	 *                                         agência automática e/ou na geração da
	 *                                         senha automática, será lançada a
	 *                                         exceção.
	 */
	public void adicionarClienteComLimiteEspecial(Cliente cliente, double limiteEspecial)
			throws InsercaoClienteException, InsercaoLimiteEspecialException, NoSuchAlgorithmException {

		validarInformacoesCliente(cliente);

		if (limiteEspecial < 500)
			throw new InsercaoLimiteEspecialException();

		var conta = new Conta(gerarNumeroAgenciaRandomico(), gerarSenha4DigitosRandomico(), limiteEspecial);

		cliente.setConta(conta);

		this.clientes.add(cliente);

	}

	private void validarInformacoesCliente(Cliente cliente) throws InsercaoClienteException {
		if (cliente.getNome() == null || cliente.getNome().trim().equals(""))
			throw new InsercaoClienteException("\"Nome\" nao existente ou invalido.");

		if (cliente.getCpf() == null || cliente.getCpf().trim().equals(""))
			throw new InsercaoClienteException("\"CPF\" nao existente ou invalido.");

		if (cliente.getRg() == null || cliente.getRg().trim().equals(""))
			throw new InsercaoClienteException("\"RG\" nao existente ou invalido.");

		if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null
				|| cliente.getEndereco().getCep().trim().equals(""))
			throw new InsercaoClienteException("\"CEP\" nao existente ou invalido.");
	}

	/**
	 * <p>
	 * Gera a senha númerica de 4 dígitos no seguinte formato:
	 * "<strong>9999</strong>"
	 * </p>
	 * 
	 * @return A senha numérica do cliente (int)
	 */
	private int gerarSenha4DigitosRandomico() throws NoSuchAlgorithmException {
		Random random = SecureRandom.getInstanceStrong();
		return random.nextInt(9999 - 1000 + 1) + 1000;
	}

	/**
	 * <p>
	 * Gera o número da agência no seguinte formato: "<strong>9999-9</strong>".
	 * </p>
	 * 
	 * @return O número da agência do cliente (String).
	 * 
	 */
	private String gerarNumeroAgenciaRandomico() throws NoSuchAlgorithmException {
		Random random = SecureRandom.getInstanceStrong();
		var sb = new StringBuilder();
		sb.append(random.nextInt(9999 - 1000 + 1) + 1000);
		sb.append("-");
		sb.append(random.nextInt(9 - 1 + 1) + 1);
		return sb.toString();
	}

}
