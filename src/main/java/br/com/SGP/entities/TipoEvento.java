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
 * @author Renato Nascimento
 */
@Entity
@Table
public class TipoEvento implements Serializable{
    
    public static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "idtipoevento",
            sequenceName = "id_tipoevento_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idtipoevento")
    private Long idtipoevento;

    @Size(min = 2, max = 255)
    @NotNull
    @Column(nullable = false)
    private String descricao;

    public Long getIdTipoEvento() {
        return idtipoevento;
    }

    public void setIdTipoEvento(Long idtipoevento) {
        this.idtipoevento = idtipoevento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoEvento() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idtipoevento);
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
        final TipoEvento other = (TipoEvento) obj;
        if (!Objects.equals(this.idtipoevento, other.idtipoevento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoEvento{" + "idcategoria=" + idtipoevento + ", nomeCategoria=" + ", descricao=" + descricao + '}';
    }
    
}
