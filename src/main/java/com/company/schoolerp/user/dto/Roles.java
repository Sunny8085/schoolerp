package com.company.schoolerp.user.dto;

public enum Roles {

	ADMIN("ADM001"),
	PRINCIPAL("PRI001"),
	VICE_PRINCIPAL("VP001"),
	TEACHER("TCH001"),
	STUDENT("STD001"),
	PARENT("PAR001"),
	ACCOUNTANT("ACC001"),
	RECEPTIONIST("REC001"),
	LIBRARIAN("LIB001"),
	HR("HR001"),
	STAFF("STF001"),
	GUEST("GST001");
	
	private final String code;
	
	Roles(String code) {
        this.code = code;
    }
	
	public String getCode() {
        return code;
    }
	
}
