/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.bean;

import br.com.SGP.dao.CadastroDao;
import br.com.SGP.dao.CategoriaProdutoDAO;
import br.com.SGP.dao.ProdutoDAO;
import br.com.SGP.entities.Cadastro;
import br.com.SGP.entities.CategoriaProduto;
import br.com.SGP.entities.Produto;
import br.com.SGP.utils.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author lucas
 */
@ManagedBean(name = "produtoMB")
@SessionScoped
public class ProdutoBean {

    private String a;
    private String imagem;
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

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
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

    public String getImagem() {

        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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
        if (img != null) {
            FacesContext facesContext = FacesContext.getCurrentInstance(); 
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            String path = scontext.getRealPath("/img/");
            //img.write("C:\\Users\\lucas\\Documents\\NetBeansProjects\\SGP-18.04\\SGP\\src\\main\\webapp\\img\\" + getDateTime() + getFilename(img));
            img.write(path + getDateTime() + getFilename(img));
            a = (getDateTime() + getFilename(img));
            produto.setImagem(a);
        }

        produtoDAO.save(produto);
        produto = new Produto();

        return "/app/sucesso?faces-redirect=true";
    }

    public String alterar() throws IOException {

        produtoDAO.save(produto);
        produto = new Produto();

        return "/app/sucesso?faces-redirect=true";
    }

    public void remover() {
        produtoDAO.remove(produto.getIdproduto());
    }

    public String editar(Produto produto) {
        this.produto = produto;
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

        return "/app/sucesso?faces-redirect=true";
    }

    public void removerCategoria() {
        categoriaDAO.remove(categoria.getIdcategoria());
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

}
