/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lucas
 */
@Entity
@Table
public class CategoriaProduto implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "idcategoria",
            sequenceName = "id_categoria_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idcategoria")
    private Long idcategoria;

    @Size(min = 2, max = 10)
    @NotNull
    @Column(nullable = false)
    private String nomeCategoria;

    @Size(min = 2, max = 255)
    @NotNull
    @Column(nullable = false)
    private String descricao;

    public Long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaProduto() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idcategoria);
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
        final CategoriaProduto other = (CategoriaProduto) obj;
        if (!Objects.equals(this.idcategoria, other.idcategoria)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategoriaProduto{" + "idcategoria=" + idcategoria + ", nomeCategoria=" + nomeCategoria + ", descricao=" + descricao + '}';
    }
    
    
    
}
