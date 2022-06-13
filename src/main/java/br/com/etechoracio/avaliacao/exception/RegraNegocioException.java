package br.com.etechoracio.avaliacao.exception;

public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException() {
        super();
    }

    public RegraNegocioException(String message) {
        super(message);
    }
}
