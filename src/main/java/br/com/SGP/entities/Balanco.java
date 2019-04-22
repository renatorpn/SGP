/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lucas
 */
@Entity
@Table
public class Balanco implements Serializable {

    public static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="idbalanco",
                       sequenceName="balanco_id_seq",
		       allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="idbalanco")
    private Long idbalanco;

    
    @Column(name = "periodo")
    private String periodo;
    
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idBalanco", cascade = CascadeType.ALL)
    private List<ItemBalanco> itemBalanco;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id", name = "idcliente_fk", nullable = false)
    private Cadastro cliente;
    

    public Balanco() {
        
    }
    
    public Long getId() {
        return idbalanco;
    }

    public void setId(Long idbalanco) {
        this.idbalanco = idbalanco;
    }

    public Long getIdbalanco() {
        return idbalanco;
    }

    public void setIdbalanco(Long idbalanco) {
        this.idbalanco = idbalanco;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }



    public List<ItemBalanco> getItemBalanco() {
        return itemBalanco;
    }

    public void setItemBalanco(List<ItemBalanco> itemBalanco) {
        this.itemBalanco = itemBalanco;
    }

    public Cadastro getCliente() {
        return cliente;
    }

    public void setCliente(Cadastro cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemBalanco item) {
        item.setIdBalanco(this);
        this.itemBalanco.add(item);    
    }

    public void removerItem(ItemBalanco item) {
        getItemBalanco().remove(item);
    }
    
    public ItemBalanco getItem(Produto produto) {
    ItemBalanco itemAProcurar = new ItemBalanco(produto);
    for (ItemBalanco item : getItemBalanco()) {
      if (item.equals(itemAProcurar)) { return item; }
    }
    return null;
  }
    
    @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Balanco other = (Balanco) obj;
    if (idbalanco == null) {
      if (other.idbalanco != null) return false;
    }
    else if (!idbalanco.equals(other.idbalanco)) return false;
    return true;
  }
  
    public void balancoRecente(Balanco b){
      int mes = 0;
      int ano = 0;
        if(this.periodo.contains("Janeiro"))
            mes = 1;
        else if (b.periodo.contains("Fevereiro"))
            mes = 2;
        else if (b.periodo.contains("Março"))
           mes = 3;
        else if (b.periodo.contains("Abril"))
            mes = 4;
        else if (b.periodo.contains("Maio"))
            mes = 5;
        else if (b.periodo.contains("Junho"))
            mes = 6;
        else if (b.periodo.contains("Julho"))
            mes = 7;
        else if (b.periodo.contains("Agosto"))
            mes = 8;
        else if (b.periodo.contains("Setembro"))
            mes = 9;
        else if (b.periodo.contains("Outubro"))
            mes = 10;
        else if (b.periodo.contains("Novembro"))
            mes = 11;
        else if (b.periodo.contains("Dezembro"))
            mes = 12;
        
        ano = Integer.parseInt(periodo.substring(periodo.length() - 3));

  }

    @Override
    public String toString() {
        return "Balanco{" + "idbalanco=" + idbalanco + ", periodo=" + periodo + ", itemBalanco=" + itemBalanco + ", cliente=" + cliente + '}';
    }
    
    
    
  
}


