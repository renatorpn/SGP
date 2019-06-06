/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.bean;

import br.com.SGP.dao.CategoriaProdutoDAO;
import br.com.SGP.dao.ProdutoDAO;
import br.com.SGP.entities.CategoriaProduto;
import br.com.SGP.entities.Produto;
import br.com.SGP.entities.Usuario;
import br.com.SGP.utils.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author lucas
 */
@ManagedBean(name = "produtoMB")
@SessionScoped
public class ProdutoBean {

    private String pathImg;
    private Part img;
    private Produto produto = new Produto();
    private CategoriaProduto categoria = new CategoriaProduto();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private CategoriaProdutoDAO categoriaDAO = new CategoriaProdutoDAO();
    private List<Produto> produtos = new ArrayList<>();
    private List<CategoriaProduto> categorias = new ArrayList<>();

    public Part getImg() {
        return img;
    }

    public void setImg(Part img) {
        this.img = img;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String cadastroProduto() {
        produto = new Produto();
        return "/app/produto/produto?faces-redirect=true";
    }

    public CorProduto[] getCorProduto() {
        return CorProduto.values();
    }

    public FxClassificacao[] getFxClassificacao() {
        return FxClassificacao.values();
    }

    public Sexo[] getSexo() {
        return Sexo.values();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        produtos = produtoDAO.findAll();
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

    public List<CategoriaProduto> getCategorias() {
        categorias = categoriaDAO.findAll();
        return categorias;
    }

    public void setCategorias(List<CategoriaProduto> categorias) {
        this.categorias = categorias;
    }

    public String cadastrar() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (verificaProduto(produto) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no cadastro.", "Produto já cadastrado!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "/app/produto/produto?faces-redirect=true";
        } else {
            if (img != null) {
                //FacesContext facesContext = FacesContext.getCurrentInstance(); 
                //ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
                //String path = scontext.getRealPath("/img/");

                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/img/");

                img.write(path + getDateTime() + getFilename(img));
                pathImg = (getDateTime() + getFilename(img));
                produto.setImagem(pathImg);
            }            
            produtoDAO.save(produto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com Sucesso.", "Produto: "+produto.getNome()));
            context.getExternalContext().getFlash().setKeepMessages(true);
            produto = new Produto();
            return "/app/produto/listarproduto?faces-redirect=true";
        }

    }

    public String alterar() throws IOException {

        produtoDAO.save(produto);
        produto = new Produto();

        return "/app/sucesso?faces-redirect=true";
    }

    public void remover() {
        produtoDAO.remove(produto.getIdproduto());
    }

    public String alterarProduto() throws IOException {
        produtoDAO.save(produto);
        FacesContext context = FacesContext.getCurrentInstance();
            if (img != null) {
                //FacesContext facesContext = FacesContext.getCurrentInstance(); 
                //ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
                //String path = scontext.getRealPath("/img/");

                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/img/");

                img.write(path + getDateTime() + getFilename(img));
                pathImg = (getDateTime() + getFilename(img));
                produto.setImagem(pathImg);
            }
            produtoDAO.save(produto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com Sucesso.", "Produto: "+produto.getNome()));
            context.getExternalContext().getFlash().setKeepMessages(true);
            produto = new Produto();
            return "/app/produto/listarproduto?faces-redirect=true";
        
    }

    public String editar(Produto p) {
        this.produto = p;
        return "/app/produto/alterarproduto?faces-redirect=true";
    }

    @PostConstruct
    public void construct() {

        produto = new Produto();
        produtos = produtoDAO.findAll();
    }

    public String cadastrarCategoria() {

        categoriaDAO.save(categoria);
        categoria = new CategoriaProduto();

        return "/app/produto/categoriaproduto?faces-redirect=true";
    }
    
    public void limparCategoria(){
        categoria = new CategoriaProduto();
    }
    
    //Verifica se categoria está sendo utilizada - Impede exclusão se sim
    public boolean verificaCategoria(CategoriaProduto categoriaExlcuir) {
        for (Produto p : produtos) {
            if (p.getCategoriaProduto().getIdcategoria().equals(categoriaExlcuir.getIdcategoria())) {
                return true;
            }
        }
        return false;
    }

    public String removerCategoria(CategoriaProduto categoriaEcluir) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (verificaCategoria(categoriaEcluir) == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha na exclusão.", "Categoria associada a Produto!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "/app/produto/categoriaproduto?faces-redirect=true";
        }else{
        categoriaDAO.remove(categoriaEcluir.getIdcategoria());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluída com Sucesso.", ""));
            context.getExternalContext().getFlash().setKeepMessages(true);
        return "/app/produto/categoriaproduto?faces-redirect=true";
        
        }
    }

    public String editarCategoria(CategoriaProduto c) {
        categoria = c;
        return "/app/produto/alterarcategoria?faces-redirect=true";
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public boolean verificaProduto(Produto novo) {
        for (Produto p : produtos) {
            if (p.getCodigo().equals(novo.getCodigo()) && p.getCor().equals(novo.getCor())) {
                return true;
            }
        }
        return false;
    }

}
