package br.edu.iftm.ecommerce.util.exception;

public class ErroNegocioException extends Exception{

    public ErroNegocioException(String message) {
        super(message);
    }

    public ErroNegocioException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErroNegocioException(Throwable cause) {
        super(cause);
    }
}
