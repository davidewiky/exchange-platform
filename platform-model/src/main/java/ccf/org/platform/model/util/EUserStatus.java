package ccf.org.platform.model.util;

import java.util.EnumSet;

public enum EUserStatus implements Mappable {

	ACTIVE("A"),
	INACTIVE("IA"),
	DELETED("D");

	private String code;

	private EUserStatus(String eCode) {
		this.code = eCode;
	}

	public static EUserStatus resolve(final String eCode) {
		return EnumSet.allOf(EUserStatus.class).stream().filter(ds -> ds.code.equalsIgnoreCase(eCode)).findFirst()
				.orElseThrow(() -> new IllegalStateException(String.format("Unsupported status %s", eCode)));
	}
	@Override
	public String getCode() {
		return code;
	}

}
