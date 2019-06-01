/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.entities;

import br.com.SGP.utils.CargoContatoCliente;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author lucas
 */
@Entity
public class ContatoCliente implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "idContatoCliente",
            sequenceName = "ContatoCliente_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idContatoCliente")
    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "nome")
    private String nome;

    @Email
    @Size(min = 2, max = 255)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "cargo")
    @Enumerated(EnumType.STRING)
    private CargoContatoCliente cargo;

    @Size(min = 11, max = 11)
    @Column(name = "telefone")
    private String telefone;
    
    @NotNull
    @ManyToOne(optional = false)
    private Cadastro cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CargoContatoCliente getCargo() {
        return cargo;
    }

    public void setCargo(CargoContatoCliente cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cadastro getCliente() {
        return cliente;
    }

    public void setCliente(Cadastro cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.cargo);
        hash = 29 * hash + Objects.hashCode(this.telefone);
        hash = 29 * hash + Objects.hashCode(this.cliente);
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
        final ContatoCliente other = (ContatoCliente) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.cargo != other.cargo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContatoCliente{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", cargo=" + cargo + ", telefone=" + telefone + ", cliente=" + cliente + '}';
    }

}
