/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.utils;

import br.com.SGP.entities.Produto;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author lucas
 */
public class ProdutoSuporte implements Serializable{
    public static final long serialVersionUID = 1L;
 
   private Produto produto;
   private Integer somaVendas;
   private Integer somaEstoques;
   private double mediaVendas;
   private double mediaEstoques;
   private double mediaGiro;
   private double mediaCobertura;
   private int ocorrencias;
   private String statusItem;

    public ProdutoSuporte() {
    }
    
    public String getStatusItem(){
         if(mediaGiro >= 0.25 && mediaCobertura < 2.5){
             this.statusItem = "Ruptura";
         }else if(mediaGiro >= 0.25 && mediaCobertura >= 2.5){
             this.statusItem = "Abastecido";
         }else if(mediaGiro < 0.25 && mediaCobertura >= 2.5){
             this.statusItem = "Atenção";
         }else{
             this.statusItem = "Descontinuado";
         }
         return statusItem;
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getSomaVendas() {
        return somaVendas;
    }

    public void setSomaVendas(Integer somaVendas) {
        this.somaVendas = somaVendas;
    }

    public Integer getSomaEstoques() {
        return somaEstoques;
    }

    public void setSomaEstoques(Integer somaEstoques) {
        this.somaEstoques = somaEstoques;
    }

    public double getMediaVendas() {
        mediaVendas = somaVendas / ocorrencias;
        return mediaVendas;
    }

    public void setMediaVendas(double mediaVendas) {
        this.mediaVendas = mediaVendas;
    }

    public double getMediaEstoques() {
        mediaEstoques = somaEstoques / ocorrencias;
        return mediaEstoques;
    }

    public void setMediaEstoques(double mediaEstoques) {
        this.mediaEstoques = mediaEstoques;
    }

    public double getMediaGiro() {
        double v = (double) somaVendas;
        double e = (double) somaEstoques;
        mediaGiro = v/(e+v);
        return mediaGiro;
    }

    public void setMediaGiro(double mediaGiro) {
        this.mediaGiro = mediaGiro;
    }

    public double getMediaCobertura() {
        Double v = (double) somaVendas;
        Double e = (double) somaEstoques;
        if (v == 0){
            mediaCobertura = (double) 0;
        }else{
        mediaCobertura = (e / v);
        }
        return mediaCobertura;
    }

    public void setMediaCobertura(double mediaCobertura) {
        this.mediaCobertura = mediaCobertura;
    }

    public int getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(int ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.produto);
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
        final ProdutoSuporte other = (ProdutoSuporte) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }


   
    
    
}
