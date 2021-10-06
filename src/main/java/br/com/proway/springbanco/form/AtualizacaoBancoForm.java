package br.com.proway.springbanco.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.proway.springbanco.model.Banco;
import br.com.proway.springbanco.repository.BancoRepository;

public class AtualizacaoBancoForm {

	@NotNull 
	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean atualizarNomeBanco(String id, BancoRepository bancoRepository) {
		Optional<Banco> banco = bancoRepository.findById(Long.parseLong(id));

		if (banco.isPresent()) {
			banco.get().setNome(getNome());
			bancoRepository.save(banco.get());
			return true;
		}

		return false;

	}

}
