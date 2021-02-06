package br.com.avaliacao.cursos.exception;

public class ValidationException extends RuntimeException {

	public ValidationException() {}

	public ValidationException(Exception e) {
		super(e);
	}

	public ValidationException(String mensagem) {
		super(mensagem);
	}

	public ValidationException(String mensagem, Throwable erro) {
		super(mensagem, erro);
	}

}
