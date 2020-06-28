package br.edu.iftm.ecommerce.util.exception;

public class ErroSistemaException extends Exception{

    public ErroSistemaException(String message) {
        super(message);
    }

    public ErroSistemaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErroSistemaException(Throwable cause) {
        super(cause);
    }
    
}
