package com.poc.contrainte.error;

public enum ErrorsEnum {

	ERR_ID_PERSON("ERR_ID_PERSON", "Id person can not be null.", Boolean.TRUE),
	ERR_PERSON_INVALID("ERR_PERSON_INVALID", "Person not valid.", Boolean.TRUE),
	;

	private final String errorCode;
	private final String errorMessage;
	private final Boolean error;

	ErrorsEnum(String errorCode, String errorMessage, Boolean error) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorCode : " + errorCode + " errorMessage : " + errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Boolean getError() {
		return error;
	}
}
