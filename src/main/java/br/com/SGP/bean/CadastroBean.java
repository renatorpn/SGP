package br.com.SGP.bean;

import br.com.SGP.dao.BalancoDAO;
import br.com.SGP.dao.CadastroDao;
import br.com.SGP.dao.ItemBalancoDAO;
import br.com.SGP.entities.Balanco;

import br.com.SGP.entities.Cadastro;
import br.com.SGP.entities.ItemBalanco;
import br.com.SGP.entities.Produto;
import br.com.SGP.utils.AmbienteVendasCliente;
import br.com.SGP.utils.CanalVendasCliente;
import br.com.SGP.utils.CategoriaCliente;
import br.com.SGP.utils.ClassificacaoClienteABC;
import br.com.SGP.utils.Estado;
import br.com.SGP.utils.ProdutoSuporte;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.Visibility;

@ManagedBean
@SessionScoped
public class CadastroBean implements Serializable {

    //Cliente
    private Cadastro cadastro = new Cadastro();
    private CadastroDao cadastroDao = new CadastroDao();
    private List<Cadastro> cadastros = new ArrayList<Cadastro>();
    private List<Cadastro> findAll;

    //Balanço
    private Balanco balanco = new Balanco();
    private BalancoDAO balancoDAO = new BalancoDAO();
    private List<Balanco> balancos = new ArrayList<Balanco>();
    private List<Balanco> findAllBalancos;
    //ItemBalanço
    private ItemBalanco itemBalanco = new ItemBalanco();
    private ItemBalancoDAO itemBalancoDAO = new ItemBalancoDAO();
    private List<ItemBalanco> itensBalanco = new ArrayList<ItemBalanco>();
    private List<Boolean> list = new ArrayList<Boolean>();

    //---------------Cliente------------------------
    public List<Cadastro> getCadastros() {
        cadastros = cadastroDao.findAll();
        return cadastros;
    }

    public Cadastro getCadastro() {

        return cadastro;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public Estado[] getEstado() {
        return Estado.values();
    }

    public ClassificacaoClienteABC[] getClassificacaoClienteabc() {
        return ClassificacaoClienteABC.values();
    }

    public AmbienteVendasCliente[] getAmbienteVendasCliente() {
        return AmbienteVendasCliente.values();
    }

    public CategoriaCliente[] getCategoriaCliente() {
        return CategoriaCliente.values();
    }

    public CanalVendasCliente[] getCanalVendasCliente() {
        return CanalVendasCliente.values();
    }

    public List<Cadastro> getFindAll() {
        return findAll;
    }

    public void setFindAll(List<Cadastro> findAll) {
        this.findAll = findAll;
    }

    //-----------------Balanço---------------------------
    public Balanco getBalanco() {
        return balanco;
    }

    public void setBalanco(Balanco balanco) {
        this.balanco = balanco;
    }

    public List<Balanco> getBalancos() {
        balancos = balancoDAO.findAllByCliente(cadastro);
        return balancos;
    }

    public void setBalancos(List<Balanco> balancos) {
        this.balancos = balancos;
    }

    public List<Balanco> getFindAllBalancos() {
        return findAllBalancos;
    }

    public void setFindAllBalancos(List<Balanco> findAllBalancos) {
        this.findAllBalancos = findAllBalancos;
    }

    //----------------ItemBalanço------------------------------
    public ItemBalanco getItemBalanco() {
        return itemBalanco;
    }

    public void setItemBalanco(ItemBalanco itemBalanco) {
        this.itemBalanco = itemBalanco;
    }

    public List<ItemBalanco> getItensBalanco() {
        itensBalanco = itemBalancoDAO.findAllByBalanco(this.balanco);
        return itensBalanco;
    }

    public void setItensBalanco(List<ItemBalanco> itensBalanco) {
        this.itensBalanco = itensBalanco;
    }

    //---------------------------------------------------------
    @PostConstruct
    public void construct() {
        //code
        balanco = new Balanco();
        findAll = cadastroDao.findAll();
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
    }

    public List<Cadastro> findAll() {
        return findAll;

    }

    public String cadastroView() {
        cadastro = new Cadastro();
        return "/app/cliente/cliente?faces-redirect=true";
    }

    public String cadastrar() {
        cadastroDao.save(cadastro);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cadastrado com successo: ",  "Cliente " + cadastro.getNome()) );
        cadastro = new Cadastro();
        findAll = cadastroDao.findAll();
        return "/app/cliente/listacliente?faces-redirect=true";
    }

    public String remover() {
        cadastroDao.remove(cadastro.getId());
        findAll = cadastroDao.findAll();
        return "/app/cliente/listacliente?faces-redirect=true";
    }

    public String editar(Cadastro cadastro) {
        this.cadastro = cadastro;
        return "/app/cliente/alterarcliente?faces-redirect=true";
    }

    public String balancoCadastrados(Cadastro cadastro) {

        return "/app/balanco/listarbalanco?faces-redirect=true";
    }

    public String mediaVendas(Cadastro cadastro) {

        return "/app/cliente/teste?faces-redirect=true";
    }

    public String editarBalanco(Balanco balanco) {
        this.balanco = balanco;
        return "/app/balanco/itens?faces-redirect=true";
    }

    public String cadastrarBalanco(Produto produto) {
        balanco.setCliente(cadastro);
        itemBalanco.setProduto(produto);
        balanco.adicionarItem(itemBalanco);
        balancoDAO.save(balanco);

        cadastro = new Cadastro();
        findAll = cadastroDao.findAll();
        return "/app/sucesso?faces-redirect=true";
    }

    public void onAddNew() {
        // Add one new item to the table:
        ItemBalanco i = new ItemBalanco();
        i = itemBalanco;
        i.setIdBalanco(balanco);
        i.setStatusItem(i.getGiro(), i.getCobertura());
        itensBalanco = balanco.getItemBalanco();
        itensBalanco.add(i);

        balanco.setItemBalanco(itensBalanco);

        balancoDAO.save(balanco);

        FacesMessage msg = new FacesMessage("Novo item adicionado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        itemBalanco = new ItemBalanco();
    }

    public String cadastrarBalanco() {
        balanco.setCliente(cadastro);
        balanco.setItemBalanco(null);
        balancoDAO.save(balanco);
        balanco = new Balanco();

        return "/app/sucesso?faces-redirect=true";
    }

    private List<ProdutoSuporte> produtosSuporte = new ArrayList<>();
    private ProdutoSuporte produtoSuporte = new ProdutoSuporte();

    public List<ProdutoSuporte> getProdutosSuporte() {
        produtosSuporte = mediaVendaProduto(cadastro);
        return produtosSuporte;
    }

    public void setProdutosSuporte(List<ProdutoSuporte> produtosSuporte) {
        this.produtosSuporte = produtosSuporte;
    }

    public ProdutoSuporte getProdutoSuporte() {
        return produtoSuporte;
    }

    public void setProdutoSuporte(ProdutoSuporte produtoSuporte) {
        this.produtoSuporte = produtoSuporte;
    }

    public List<ProdutoSuporte> mediaVendaProduto(Cadastro cliente) {
        List<ProdutoSuporte> produtos = new ArrayList<>();
        List<Balanco> listaBalanco = balancoDAO.findAllByCliente(cliente);

        for (int a = 0; a < listaBalanco.size(); a++) {
            List<ItemBalanco> listaItemBalanco = itemBalancoDAO.findAllByBalanco(listaBalanco.get(a));

            for (int i = 0; i < listaItemBalanco.size(); i++) {

                ProdutoSuporte pscheck = new ProdutoSuporte();
                pscheck.setProduto(listaItemBalanco.get(i).getProduto());
                if (!produtos.contains(pscheck)) {
                    pscheck.setSomaVendas(0);
                    pscheck.setSomaEstoques(0);
                    produtos.add(pscheck);
                }

                for (ProdutoSuporte ps : produtos) {

                    if (ps.getProduto().equals(listaItemBalanco.get(i).getProduto())) {
                        int valvenda = listaItemBalanco.get(i).getQtdVenda() + ps.getSomaVendas();
                        ps.setSomaVendas(valvenda);
                        int valestoque = listaItemBalanco.get(i).getQtdEstoque() + ps.getSomaEstoques();
                        ps.setSomaEstoques(valestoque);
                        ps.setOcorrencias(ps.getOcorrencias() + 1);
                    }
                }
            }
        }
        return produtos;
    }

//------------- Toggler
    public List<Boolean> getList() {
        return list;
    }

    public void setList(List<Boolean> list) {
        this.list = list;
    }

    public void onToggle(ToggleEvent e) {
        list.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
    }

}
