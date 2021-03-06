package br.com.proway.springbanco.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.InputMismatchException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "enderecos")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cep", columnDefinition = "VARCHAR", length = 9, unique = true, updatable = false, nullable = false)
	private String cep;

	@Column(name = "logradouro", columnDefinition = "VARCHAR", unique = false, updatable = true, nullable = true)
	private String logradouro;

	@Column(name = "bairro", columnDefinition = "VARCHAR", unique = false, updatable = true, nullable = true)
	private String bairro;

	@Column(name = "cidade", columnDefinition = "VARCHAR", unique = false, updatable = true, nullable = true)
	private String cidade;

	@Column(name = "uf", columnDefinition = "VARCHAR", length = 2, unique = false, updatable = true, nullable = true)
	private String uf;

	/**
	 * <p>
	 * Cria um novo endereco consultando o "viacep" para gerar os dados do endereco
	 * de forma automatica atraves do CEP.
	 * </p>
	 * 
	 * @param cep - CEP (Integer)
	 * 
	 * @throws InputMismatchException Caso o CEP seja passado na formatacao
	 *                                incorreta, sera lancada a excecao.
	 * 
	 * @throws IOException            Caso ocorra algum erro ao tentar gerar as
	 *                                informações do endereco atraves do "viacep", a
	 *                                excessao sera lancada.
	 */
	public Endereco(Integer cep) throws InputMismatchException, IOException {

		if (!validarCep(cep))
			throw new InputMismatchException("Formato do CPF invalido.");

		String[] endereco = buscarCep(cep);

		setCep(endereco[0]);
		setLogradouro(endereco[1]);
		setBairro(endereco[2]);
		setCidade(endereco[3]);
		setUf(endereco[4]);
	}

	/**
	 * <p>
	 * Cria um novo endereco manualmente.
	 * </p>
	 * 
	 * @param cep        - CEP (String)
	 * @param logradouro - Logradouro (String)
	 * @param bairro     - Bairro (String)
	 * @param cidade     - Cidade (String)
	 * @param uf         - UF (String)
	 * 
	 * @throws InputMismatchException Caso o CEP seja passado na formatacao
	 *                                incorreta, sera lancada a excecao.
	 */
	public Endereco(String cep, String logradouro, String bairro, String cidade, String uf)
			throws InputMismatchException {

		if (!validarCep(cep))
			throw new InputMismatchException("Formato do CPF invalido.");

		setCep(cep);
		setLogradouro(logradouro);
		setBairro(bairro);
		setCidade(cidade);
		setUf(uf);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\tID: " + getId());
		sb.append("\n\tCEP: " + getCep());
		sb.append("\n\tLogradouro: " + getLogradouro());
		sb.append("\n\tBairro: " + getBairro());
		sb.append("\n\tCidade: " + getCidade());
		sb.append("\n\tUF: " + getUf());
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	private static String[] buscarCep(Integer cepInt) throws IOException {

		var url = new URL("http://viacep.com.br/ws/" + cepInt + "/json");
		var urlConnection = url.openConnection();
		var is = urlConnection.getInputStream();
		var br = new BufferedReader(new InputStreamReader(is));

		var jsonSb = new StringBuilder();

		br.lines().forEach(l -> jsonSb.append(l.trim()));

		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(jsonSb.toString());

		final String CEP = "cep";
		final String LOGRADOURO = "logradouro";
		final String BAIRRO = "bairro";
		final String LOCALIDADE = "localidade";
		final String UF = "uf";

		String cep;
		String logradouro;
		String bairro;
		String cidade;
		String uf;

		if (json.get(CEP) != null && json.get(CEP).textValue().getBytes().length > 0) {
			cep = json.get(CEP).textValue();
		} else {
			cep = "";
		}

		if (json.get(LOGRADOURO) != null && json.get(LOGRADOURO).textValue().getBytes().length > 0) {
			logradouro = json.get(LOGRADOURO).textValue();
		} else {
			logradouro = "";
		}

		if (json.get(BAIRRO) != null && json.get(BAIRRO).textValue().getBytes().length > 0) {
			bairro = json.get(BAIRRO).textValue();
		} else {
			bairro = "";
		}

		if (json.get(LOCALIDADE) != null && json.get(LOCALIDADE).textValue().getBytes().length > 0) {
			cidade = json.get(LOCALIDADE).textValue();
		} else {
			cidade = "";
		}

		if (json.get(UF) != null && json.get(UF).textValue().getBytes().length > 0) {
			uf = json.get(UF).textValue();
		} else {
			uf = "";
		}

		return new String[] { cep, logradouro, bairro, cidade, uf };

	}

	private boolean validarCep(String cep) {
		return cep.matches("[0-9]{5}-[0-9]{3}");
	}

	private boolean validarCep(Integer cep) {
		return String.valueOf(cep).matches("[0-9]{8}");
	}

}
