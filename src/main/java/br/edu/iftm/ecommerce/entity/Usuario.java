package br.edu.iftm.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    
}
