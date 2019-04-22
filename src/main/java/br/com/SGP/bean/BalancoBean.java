/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.bean;

import br.com.SGP.dao.BalancoDAO;
import br.com.SGP.entities.Balanco;
import br.com.SGP.entities.Cadastro;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lucas
 */
@ManagedBean(name = "balancoMB")
@SessionScoped
public class BalancoBean {
    

    private Balanco balanco;
    private BalancoDAO balancoDAO;
    private List<Balanco> balancos = new ArrayList<Balanco>();
    private List<Balanco> findAllBalancos;
    
    public Balanco getBalanco() {
        return balanco;
    }

    public void setBalanco(Balanco balanco) {
        this.balanco = balanco;
    }

    public BalancoDAO getBalancoDAO() {
        return balancoDAO;
    }

    public void setBalancoDAO(BalancoDAO balancoDAO) {
        this.balancoDAO = balancoDAO;
    }

    public List<Balanco> getBalancos() {
        return balancos;
    }

    public void setBalancos(List<Balanco> balancos) {
        this.balancos = balancos;
    }
    

        
        public List<Balanco> findAllBalancos() {
        return findAllBalancos;
    }
}
