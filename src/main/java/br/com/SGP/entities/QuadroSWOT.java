/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author renas
 */
@Entity
@Table
public class QuadroSWOT implements Serializable {
    
    public static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="idquadroswot",
                       sequenceName="balanco_id_seq",
		       allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="idquadroswot")
    private Long idquadroswot;
    
    @OneToOne(optional = false)
    @JoinColumn(referencedColumnName = "id", name = "idcliente_fk", nullable = false)
    private Cadastro cliente;
    
    private String pontosFortes;
    private String pontosFracos;
    private String ameacas;
    private String oportunidades;

    public QuadroSWOT() {
    }

    public Long getIdquadroswot() {
        return idquadroswot;
    }

    public void setIdquadroswot(Long idquadroswot) {
        this.idquadroswot = idquadroswot;
    }

    public Cadastro getCliente() {
        return cliente;
    }

    public void setCliente(Cadastro cliente) {
        this.cliente = cliente;
    }

    public String getPontosFortes() {
        return pontosFortes;
    }

    public void setPontosFortes(String pontosFortes) {
        this.pontosFortes = pontosFortes;
    }

    public String getPontosFracos() {
        return pontosFracos;
    }

    public void setPontosFracos(String pontosFracos) {
        this.pontosFracos = pontosFracos;
    }

    public String getAmeacas() {
        return ameacas;
    }

    public void setAmeacas(String ameacas) {
        this.ameacas = ameacas;
    }

    public String getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(String oportunidades) {
        this.oportunidades = oportunidades;
    }

    @Override
    public String toString() {
        return "QuadroSWOT{" + "idquadroswot=" + idquadroswot + ", cliente=" + cliente + ", pontosFortes=" + pontosFortes + ", pontosFracos=" + pontosFracos + ", ameacas=" + ameacas + ", oportunidades=" + oportunidades + '}';
    }
}
