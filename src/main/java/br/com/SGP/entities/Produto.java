/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.entities;

import br.com.SGP.utils.CorProduto;
import br.com.SGP.utils.FxClassificacao;
import br.com.SGP.utils.Sexo;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author lucas
 */
@Entity
@Table
public class Produto implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="idproduto",
                       sequenceName="id_produto_seq",
		       allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="idproduto")
    private Long idproduto;
    
    @Size(min = 2, max = 100)
    @NotNull
    @Column(nullable = false)
    private String nome;
    
    @Size(min = 6, max = 6)
    @NotNull
    @Column(nullable = false)
    private String codigo;
    
    @Column(nullable = true)
    private String imagem;
    
   
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CorProduto cor;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FxClassificacao fxclassificacao;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo genero;
    
    @NotNull
    private Double precovenda;
    
    @NotNull
    @OneToOne
    @JoinColumn (name = "id_categoria_fk")
    private CategoriaProduto categoriaProduto;

    public Long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

     public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public CorProduto getCor() {
        return cor;
    }

    public void setCor(CorProduto cor) {
        this.cor = cor;
    }

    public FxClassificacao getFxclassificacao() {
        return fxclassificacao;
    }

    public void setFxclassificacao(FxClassificacao fxclassificacao) {
        this.fxclassificacao = fxclassificacao;
    }

    public Sexo getGenero() {
        return genero;
    }

    public void setGenero(Sexo genero) {
        this.genero = genero;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    
    public Double getPrecovenda() {
        return precovenda;
    }

    public void setPrecovenda(Double precovenda) {
        this.precovenda = precovenda;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.idproduto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.idproduto, other.idproduto)) {
            return false;
        }
        return true;
    }

        
    
    
    
    
}

