package br.com.proway.springbanco.banco;

import java.security.NoSuchAlgorithmException;

import br.com.proway.springbanco.excecoes.InsercaoClienteException;
import br.com.proway.springbanco.excecoes.InsercaoLimiteEspecialException;
import br.com.proway.springbanco.model.Banco;
import br.com.proway.springbanco.model.Cliente;

public class BancoEstatico {

	public static Banco banco1;
	public static Banco banco2;

	static {
		banco1 = new Banco(1L, "Itaú", "88.105.140/0001-75", 0572);
		banco2 = new Banco(2L, "Bradesco", "60.009.694/0001-50", 7092);

		var cliente1 = new Cliente(1L, "João", "582.303.140-80", "32.124.432-1", "Rua A");
		var cliente2 = new Cliente(2L, "Maria", "477.490.430-98", "40.758.213-7", "Rua B");
		var cliente3 = new Cliente(3L, "Carlos", "192.538.220-66", "32.573.072-6", "Rua C");
		var cliente4 = new Cliente(4L, "Ana", "511.133.800-47", "26.923.291-6", "Rua D");

		try {
			banco1.adicionarClienteComLimiteEspecial(cliente1, 1000);
			banco1.adicionarClienteComLimiteEspecial(cliente2, 2000);
			banco2.adicionarClienteComLimiteEspecial(cliente3, 3000);
			banco2.adicionarClienteComLimiteEspecial(cliente4, 4000);
		} catch (NoSuchAlgorithmException | InsercaoClienteException | InsercaoLimiteEspecialException e) {
			System.out.println(e.getMessage());
		}

	}

}
