/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.entities;

/**
 *
 * @author lucas
 */
import br.com.SGP.utils.VendaRealizadaEvento;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Renato
 */
@Entity
@Table
public class Evento implements Serializable{

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idevento;

    @Size(min = 2, max = 50)
    @NotNull
    @Column(nullable = false)
    private String nomeevento;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Temporal(TemporalType.TIME)
    private Date horaFim;

    private Double investimento;

    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @OneToOne
    @JoinColumn (name = "id_tipoevento_fk")
    private TipoEvento tipoevento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VendaRealizadaEvento vendarealizada;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "idcliente_fk")
    private Cadastro cliente;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "idpresentante_fk")
    private Usuario representante;

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public Long getIdevento() {
        return idevento;
    }

    public void setIdevento(Long idevento) {
        this.idevento = idevento;
    }

    public String getNomeevento() {
        return nomeevento;
    }

    public void setNomeevento(String nomeevento) {
        this.nomeevento = nomeevento;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Double getInvestimento() {
        return investimento;
    }

    public void setInvestimento(Double investimento) {
        this.investimento = investimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoEvento getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(TipoEvento tipoevento) {
        this.tipoevento = tipoevento;
    }

    public VendaRealizadaEvento getVendarealizada() {
        return vendarealizada;
    }

    public void setVendarealizada(VendaRealizadaEvento vendarealizada) {
        this.vendarealizada = vendarealizada;
    }

    public Cadastro getCliente() {
        return cliente;
    }

    public void setCliente(Cadastro cliente) {
        this.cliente = cliente;
    }

    public Usuario getRepresentante() {
        return representante;
    }


    public void setRepresentante(Usuario representante) {
        this.representante = representante;



}

    @Override
    public String toString() {
        return "Evento{" + "idevento=" + idevento + ", nomeevento=" + nomeevento + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", investimento=" + investimento + ", descricao=" + descricao + ", tipoevento=" + tipoevento + ", vendarealizada=" + vendarealizada + ", cliente=" + cliente + ", representante=" + representante + '}';
    }
}
