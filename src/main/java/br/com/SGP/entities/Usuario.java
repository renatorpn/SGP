/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.entities;
import br.com.SGP.utils.Cargo;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Email;

@Entity
public class Usuario implements Serializable{
    public static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="idusuario",
                       sequenceName="id_usuario_seq",
		       allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="idusuario")
    @Column(name="id", nullable=false, unique=true)
    private Long id;

    @Column(name="userName", nullable=false, unique=true)
    private String nomeUsuario;
    
    @Column(name="nome", nullable=false, unique=false)
    private String nome;

    @Column(name="password", nullable=false, unique=false)
    private String senha;
    
    @Email
    @Column(name="email", nullable=false, unique=false)
    private String email;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    
        
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "representante", cascade = CascadeType.REMOVE)
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    private List<Cadastro> idCliente;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cadastro> getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(List<Cadastro> idCliente) {
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

       
    
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        return true;
    }
    
    
    

    
    
}