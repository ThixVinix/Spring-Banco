package br.com.proway.springbanco.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.springbanco.banco.BancoEstatico;
import br.com.proway.springbanco.dto.BancoDTO;
import br.com.proway.springbanco.dto.ClienteDTO;
import br.com.proway.springbanco.form.AtualizacaoBancoForm;
import br.com.proway.springbanco.model.Banco;
import br.com.proway.springbanco.model.Cliente;
import br.com.proway.springbanco.repository.BancoRepository;

@RestController
@RequestMapping("/banco")
public class BancoController {

	@Autowired
	private BancoRepository bancoRepository;

	@GetMapping("/listarBancos")
	public List<BancoDTO> listarBancos() {
		// List<Banco> bancos = Arrays.asList(BancoEstatico.banco1,
		// BancoEstatico.banco2);

		// Optional<List<BancoDTO>> bancosDto = BancoDTO.converter(bancos);

		List<Banco> bancos = bancoRepository.findAll();

		Optional<List<BancoDTO>> bancosDto = BancoDTO.converter(bancos);
		if (bancosDto.isPresent()) {
			return bancosDto.get();
		} else {
			return Collections.emptyList();
		}

	}

	@GetMapping("/{bancoId}/listarClientes")
	public List<ClienteDTO> listarClientes(@PathVariable String bancoId) {

		List<Cliente> clientes = BancoEstatico.banco1.getClientes();

		Optional<List<ClienteDTO>> clientesDto = ClienteDTO.converter(clientes);

		if (clientesDto.isPresent()) {
			return clientesDto.get();
		} else {
			return Collections.emptyList();
		}

	}

//	@PostMapping("/salvar")
//	public String salvar(@RequestBody UsuarioForm userForm) {
//
//		try {
//			userForm.converter();
//			return "Usuário salvo com sucesso";
//		} catch (InputMismatchException e) {
//			return e.getMessage();
//		}
//
//	}

	@PutMapping("/{id}")
	public String atualizar(@PathVariable String id, @RequestBody @Valid AtualizacaoBancoForm bancoForm) {
		
		 boolean wasModified = bancoForm.atualizarNomeBanco(id, bancoRepository);
		
			if (wasModified) {
				return "Nome do Banco alterado com sucesso!";
			} else {
				return "Não foi possível modificar o nome do banco";
			}

	}

//	@DeleteMapping("/{id}")
//	public String deletar(@PathVariable String id) {
//
//		List<Usuario> users = buscarUsuariosBanco();
//
//		Optional<Usuario> userFound = users.stream().filter(user -> user.getId().equals(Long.getLong(id))).findFirst();
//
//		if (userFound.isPresent()) {
//			users.remove(userFound.get());
//			System.out.println(users);
//			return "Usuário removido com sucesso!";
//		} else {
//			return "Usuário não existente. Não foi possível remover o usuário.";
//		}
//
//	}

}
