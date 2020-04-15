package ccf.org.platform.model.converter;

import ccf.org.platform.model.util.EUserStatus;

public class UserStatusConverter extends MappableConverter<EUserStatus> {

	@Override
	public EUserStatus convertToEntityAttribute(String dbData) {
		return EUserStatus.resolve(dbData);
	}

}
