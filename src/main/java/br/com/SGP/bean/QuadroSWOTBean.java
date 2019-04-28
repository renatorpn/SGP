/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.bean;

import br.com.SGP.dao.CadastroDao;
import br.com.SGP.dao.QuadroSWOTDAO;
import br.com.SGP.entities.Cadastro;
import br.com.SGP.entities.QuadroSWOT;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import javax.annotation.PostConstruct;

/**
 *
 * @author renas
 */

@ManagedBean(name = "quadroSWOTMB")
@SessionScoped
public class QuadroSWOTBean {
    
    private Cadastro cadastro = new Cadastro();
    private QuadroSWOT quadroSWOT = new QuadroSWOT();
    private CadastroDao cadastroDAO = new CadastroDao();
    private QuadroSWOTDAO quadroSWOTDAO = new QuadroSWOTDAO();

    public Cadastro getCadastro() {
        return cadastro;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public QuadroSWOT getQuadroSWOT() {
        return quadroSWOT;
    }

    public void setQuadroSWOT(QuadroSWOT quadroSWOT) {
        this.quadroSWOT = quadroSWOT;
    }

    public CadastroDao getCadastroDAO() {
        return cadastroDAO;
    }

    public void setCadastroDAO(CadastroDao cadastroDAO) {
        this.cadastroDAO = cadastroDAO;
    }

    public QuadroSWOTDAO getQuadroSWOTDAO() {
        return quadroSWOTDAO;
    }

    public void setQuadroSWOTDAO(QuadroSWOTDAO quadroSWOTDAO) {
        this.quadroSWOTDAO = quadroSWOTDAO;
    }
    
    public String cadastroQuadroSWOT(){
        quadroSWOT = new QuadroSWOT();
        return "/app/swot/quadroswot?faces-redirect=true";
    }
    
    public String cadastrar() throws IOException {

        quadroSWOTDAO.save(quadroSWOT);
        quadroSWOT = new QuadroSWOT();

        return "/app/sucesso?faces-redirect=true";
    }

    public String alterar() throws IOException {

        quadroSWOTDAO.save(quadroSWOT);
        quadroSWOT = new QuadroSWOT();

        return "/app/sucesso?faces-redirect=true";
    }

    public String editar(QuadroSWOT quadroSWOT) {
        this.quadroSWOT = quadroSWOT;
        return "/app/swot/quadroswot?faces-redirect=true";
    }

    /*
    @PostConstruct
    public void construct() {

        quadroSWOT = new QuadroSWOT();
    }
    */
    
    
}