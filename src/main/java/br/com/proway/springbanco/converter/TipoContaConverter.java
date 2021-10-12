package br.com.proway.springbanco.converter;

import javax.persistence.AttributeConverter;

import br.com.proway.springbanco.enums.TipoContaEnum;

public class TipoContaConverter implements AttributeConverter<TipoContaEnum, String> {

	@Override
	public String convertToDatabaseColumn(TipoContaEnum tipoContaEnum) {
		return tipoContaEnum != null ? tipoContaEnum.getCodigo() : null;
	}

	@Override
	public TipoContaEnum convertToEntityAttribute(String tipoContaString) {
		return tipoContaString != null ? TipoContaEnum.valorOfCodigo(tipoContaString) : null;
	}


	
	
}
