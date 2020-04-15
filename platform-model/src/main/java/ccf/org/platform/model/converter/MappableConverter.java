package ccf.org.platform.model.converter;

import ccf.org.platform.model.util.Mappable;

import javax.persistence.AttributeConverter;

public abstract class MappableConverter<T extends Mappable> implements AttributeConverter<T, String> {
	
	@Override
	public String convertToDatabaseColumn(T eStatus) {
		return eStatus.getCode();
	}

}
