package br.com.proway.springbanco.enums;

public enum TipoContaEnum {

	NORMAL("NORMAL"), ESPECIAL("ESPECIAL");

	private String tipo;

	TipoContaEnum(String tipo) {
		this.tipo = tipo;
	}

	public static TipoContaEnum valorOfCodigo(String tipo) {
		for (TipoContaEnum tipoContaEnum : values()) {
			if (tipoContaEnum.tipo.equalsIgnoreCase(tipo)) {
				return tipoContaEnum;
			}
		}
		
		throw new IllegalArgumentException("Tipo de conta não encontrado=" + tipo);
	}

	public String getCodigo() {
		return tipo;
	}

}
