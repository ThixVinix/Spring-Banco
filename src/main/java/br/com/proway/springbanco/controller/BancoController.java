package br.com.proway.springbanco.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.proway.springbanco.dto.BancoDTO;
import br.com.proway.springbanco.form.AtualizacaoBancoForm;
import br.com.proway.springbanco.form.BancoForm;
import br.com.proway.springbanco.model.Banco;
import br.com.proway.springbanco.repository.BancoRepository;

@RestController
@RequestMapping("/banco")
public class BancoController {

	private static final String MSG_CREATE_BANCO_SUCCESS = "Banco salvo com sucesso!";
	private static final String MSG_EDIT_BANCO_SUCCESS = "Banco alterado com sucesso!";
	private static final String MSG_DELETE_BANCO_SUCCESS = "Banco removido com sucesso!";
	private static final String STATUS = "status";
	private static final String MENSAGEM = "mensagem";

	private static final Logger LOGGER = LogManager.getLogger(BancoController.class.getName());

	@Autowired
	private BancoRepository bancoRepository;

	@Autowired
	private ObjectMapper mapper;

	@GetMapping("/listarBancos")
	public ObjectNode listarBancos() {

		ObjectNode response = mapper.createObjectNode();

		List<Banco> bancos = bancoRepository.findAll();

		Optional<List<BancoDTO>> bancosDto = BancoDTO.converter(bancos);
		if (bancosDto.isPresent()) {
			response.put(STATUS, 200);
			response.put(MENSAGEM, bancos.size() + " Banco(s) foi/foram encontrado(s).");
			response.putPOJO("bancos", bancosDto.get());
		} else {
			response.put(STATUS, 200);
			response.put(MENSAGEM, "Nenhum Banco foi encontrado.");
			response.putPOJO("bancos", Collections.emptyList());
		}

		return response;

	}

	@PostMapping("/salvar")
	public ObjectNode salvar(@RequestBody @Valid BancoForm bancoForm) {

		ObjectNode response = mapper.createObjectNode();

		try {
			LOGGER.debug("Salvando Banco...");
			Banco banco = bancoForm.converter();
			bancoRepository.save(banco);
			response.put(STATUS, 200);
			response.put(MENSAGEM, MSG_CREATE_BANCO_SUCCESS);
			LOGGER.info(MSG_CREATE_BANCO_SUCCESS);
		} catch (Exception e) {
			LOGGER.info(e.getCause());
			response.put(STATUS, "501");
			response.put(MENSAGEM, "Não foi possivel salvar o Banco (" + e.getCause() + ")");
		}

		return response;

	}

	@PutMapping("/editar/{id}")
	@Transactional
	public ObjectNode atualizar(@PathVariable String id, @RequestBody @Valid AtualizacaoBancoForm bancoForm) {

		ObjectNode response = mapper.createObjectNode();

		boolean wasModified = bancoForm.atualizarNomeBanco(id, bancoRepository);

		if (wasModified) {
			response.put(STATUS, 200);
			response.put(MENSAGEM, MSG_EDIT_BANCO_SUCCESS);
		} else {
			response.put(STATUS, 501);
			response.put(MENSAGEM, "Não foi possivel editar o banco.");
		}

		return response;
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<ObjectNode> deletar(@PathVariable String id) {

		ObjectNode response = mapper.createObjectNode();

		try {
			bancoRepository.deleteById(Long.parseLong(id));
			response.put(STATUS, 200);
			response.put(MENSAGEM, MSG_DELETE_BANCO_SUCCESS);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put(STATUS, HttpStatus.NOT_FOUND.value());
			response.put(MENSAGEM, "Não foi possível remover o Banco. (" + e.getCause() + ")");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

	}

}
