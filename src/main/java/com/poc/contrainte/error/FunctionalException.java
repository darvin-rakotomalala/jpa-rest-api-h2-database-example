package com.poc.contrainte.error;

public class FunctionalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorsEnum errorsEnum;

	public FunctionalException(ErrorsEnum errorEnum, Exception e) {
		super(e);
		this.errorsEnum = errorEnum;
	}

	public FunctionalException(ErrorsEnum errorEnum) {
		super();
		this.errorsEnum = errorEnum;
	}

	public ErrorsEnum getErrorsEnum() {
		return errorsEnum;
	}

	public void setErrorsEnum(ErrorsEnum errorsEnum) {
		this.errorsEnum = errorsEnum;
	}
}
