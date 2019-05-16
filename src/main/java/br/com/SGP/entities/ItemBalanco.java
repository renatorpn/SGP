/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.entities;

import br.com.SGP.entities.Balanco;
import br.com.SGP.entities.Produto;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "itembalanco")
public class ItemBalanco implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="idItemBalanco",
                       sequenceName="itemBalanco_id_seq",
		       allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="idItemBalanco")
    private Long idItemBalanco;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "idbalanco", name = "idbalanco_fk")
    private Balanco idBalanco;

    @ManyToOne
    @JoinColumn(referencedColumnName = "idproduto", name = "idproduto_fk")
    private Produto produto;

    @Min(0)
    private Integer qtdVenda;

    @Min(0)
    private Integer qtdEstoque;
    
    @Transient
    private double giro;
    @Transient
    private double cobertura;
    
    @NotNull
    private String statusItem;

    public ItemBalanco() {
    }

    public ItemBalanco(Produto produto) {
        this.produto = produto;
    }
    
    public ItemBalanco(Produto produto, Integer qtdVenda, Integer qtdEstoque) {
        this.produto = produto;
        this.qtdVenda = qtdVenda;
        this.qtdEstoque = qtdEstoque;
    }
    

    public Long getIdItemBalanco() {
        return idItemBalanco;
    }

    public void setIdItemBalanco(Long idItemBalanco) {
        this.idItemBalanco = idItemBalanco;
    }

    public Balanco getIdBalanco() {
        return idBalanco;
    }

    public void setIdBalanco(Balanco idBalanco) {
        this.idBalanco = idBalanco;
    }

    public Integer getQtdVenda() {
        return qtdVenda;
    }

    public void setQtdVenda(Integer qtdVenda) {
        this.qtdVenda = qtdVenda;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public String getStatusItem() {
        return statusItem;
    }

    public void setStatusItem(String statusItem) {
        this.statusItem = statusItem;
    }
    
    public double getGiro() {
        Double v = (double) qtdVenda;
        Double e = (double) qtdEstoque;
        giro = ( v + e ) / e;
        return giro;
    }
    
    public double getCobertura() {
        Double v = (double) qtdVenda;
        Double e = (double) qtdEstoque;
        if (v == 0){
            cobertura = (double) 0;
        }else{
        cobertura = (e / v);
        }
        return cobertura;
    }
    
        public void setStatusItem(double giro, double cobertura){
         if(giro >= 0.25 && cobertura < 2.5){
             this.statusItem = "Ruptura";
         }else if(giro >= 0.25 && cobertura >= 2.5){
             this.statusItem = "Abastecido";
         }else if(giro < 0.25 && cobertura >= 2.5){
             this.statusItem = "Atenção";
         }else{
             this.statusItem = "Descontinuado";
         }
         this.statusItem = statusItem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idItemBalanco);
        hash = 83 * hash + Objects.hashCode(this.idBalanco);
        hash = 83 * hash + Objects.hashCode(this.produto);
        hash = 83 * hash + Objects.hashCode(this.qtdVenda);
        hash = 83 * hash + Objects.hashCode(this.qtdEstoque);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.giro) ^ (Double.doubleToLongBits(this.giro) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.cobertura) ^ (Double.doubleToLongBits(this.cobertura) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.statusItem);
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
        final ItemBalanco other = (ItemBalanco) obj;
        if (Double.doubleToLongBits(this.giro) != Double.doubleToLongBits(other.giro)) {
            return false;
        }
        if (Double.doubleToLongBits(this.cobertura) != Double.doubleToLongBits(other.cobertura)) {
            return false;
        }
        if (!Objects.equals(this.statusItem, other.statusItem)) {
            return false;
        }
        if (!Objects.equals(this.idItemBalanco, other.idItemBalanco)) {
            return false;
        }
        if (!Objects.equals(this.idBalanco, other.idBalanco)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.qtdVenda, other.qtdVenda)) {
            return false;
        }
        if (!Objects.equals(this.qtdEstoque, other.qtdEstoque)) {
            return false;
        }
        return true;
    }
    
   
    
    

}
