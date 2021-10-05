package br.com.proway.springbanco.excecoes;


public class InsercaoClienteException extends Exception {

	private static final long serialVersionUID = -2292247522158647431L;
	
	public InsercaoClienteException() {
		super("Erro ao tentar adicionar o cliente ao banco.");
	}
	
	public InsercaoClienteException(String msg) {
		super(msg);
	}

}
