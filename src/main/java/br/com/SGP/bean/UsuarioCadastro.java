/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.bean;

import br.com.SGP.dao.UsuarioDAO;
import br.com.SGP.entities.Usuario;
import br.com.SGP.utils.Cargo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lucas
 */
@ManagedBean(name = "Usuario")
@SessionScoped
public class UsuarioCadastro {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuarioCadastro = new Usuario();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Usuario usuarioSelecionado = new Usuario();

    public UsuarioCadastro() {
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public UsuarioCadastro(Usuario usuario) {
        this.usuarioCadastro = usuario;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Cargo[] getCargo() {
        return Cargo.values();
    }

    @PostConstruct
    public void construct() {

        usuarioCadastro = new Usuario();
        usuarios = usuarioDAO.findAll();
    }

    public boolean verifica(Usuario novo) {
        for (Usuario u : usuarios) {
            if (u.getNomeUsuario().equals(novo.getNomeUsuario())) {
                return true;
            }
        }
        return false;
    }

    public void cadastrar() {
        if (verifica(usuarioCadastro) == false) {
            usuarioDAO.inserirUsuario(usuarioCadastro);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Cadastrado!"));
            usuarioCadastro = new Usuario();
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Usuário já cadastrado!"));
            usuarioCadastro = new Usuario();
        }
        usuarios = usuarioDAO.findAll();
    }

    public void alterar() {
        usuarioDAO.inserirUsuario(usuarioCadastro);
        limparUsuario();
        usuarios = usuarioDAO.findAll();
    }

    public void editar(Usuario usuario) {
        this.usuarioCadastro = usuario;

    }

    public void excluir(Usuario usuario, Usuario logado) {
        if (usuario.getId().equals(logado.getId())) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "O Administrador do Sistema não pode ser excluído.", ""));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } else {
            usuarioDAO.deletarUsuario(usuario.getId());
            usuarios = usuarioDAO.findAll();
        }
    }

    public void limparUsuario() {
        usuarioCadastro = new Usuario();
    }

}
