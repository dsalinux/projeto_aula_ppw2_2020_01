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
