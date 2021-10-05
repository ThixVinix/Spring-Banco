package br.com.proway.springbanco.excecoes;

public class InsercaoLimiteEspecialException extends Exception {

	private static final long serialVersionUID = 1715945094940952312L;

	private static final Double LIMITE_MIN_ESPECIAL = 500d;

	public InsercaoLimiteEspecialException() {
		super("Limite especial deve ser maior do que \"" + LIMITE_MIN_ESPECIAL + "\"");
	}

	public InsercaoLimiteEspecialException(String msg) {
		super(msg);
	}

}
