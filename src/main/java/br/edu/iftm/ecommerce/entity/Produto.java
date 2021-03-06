package br.edu.iftm.ecommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="produto")
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private String descricao;
    private String detalhes;
    private BigDecimal valor;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_criacao")
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_desativacao")
    private Date dataDesativacao;
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    
}
