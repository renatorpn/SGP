/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.utils;

/**
 *
 * @author lucas
 */
public enum VendaRealizadaEvento {
   SIM("Sim"), 
   NAO("Não");

    private String descricao;

    VendaRealizadaEvento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
