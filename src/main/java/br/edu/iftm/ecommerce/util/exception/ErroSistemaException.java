/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.util.exception;

/**
 *
 * @author PICHAU
 */
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
