package br.com.SGP.bean;

import br.com.SGP.dao.BalancoDAO;
import br.com.SGP.dao.CadastroDao;
import br.com.SGP.dao.ContatoClienteDAO;
import br.com.SGP.dao.ItemBalancoDAO;
import br.com.SGP.dao.QuadroSWOTDAO;
import br.com.SGP.dao.UsuarioDAO;
import br.com.SGP.entities.Balanco;

import br.com.SGP.entities.Cadastro;
import br.com.SGP.entities.ContatoCliente;
import br.com.SGP.entities.ItemBalanco;
import br.com.SGP.entities.Produto;
import br.com.SGP.entities.QuadroSWOT;
import br.com.SGP.entities.Usuario;
import br.com.SGP.utils.AmbienteVendasCliente;
import br.com.SGP.utils.CanalVendasCliente;
import br.com.SGP.utils.CargoContatoCliente;
import br.com.SGP.utils.CategoriaCliente;
import br.com.SGP.utils.ClassificacaoClienteABC;
import br.com.SGP.utils.Estado;
import br.com.SGP.utils.ProdutoSuporte;
import java.io.IOException;

import java.io.Serializable;
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
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

@ManagedBean
@SessionScoped
public class CadastroBean implements Serializable {

    //Cliente
    private Cadastro cadastro = new Cadastro();
    private CadastroDao cadastroDao = new CadastroDao();
    private List<Cadastro> cadastros = new ArrayList<Cadastro>();
    private List<Cadastro> findAll;
    private String pathImg;
    private Part img;
    //ContatoCliente
    private ContatoCliente contato = new ContatoCliente();
    private ContatoClienteDAO contatoDAO = new ContatoClienteDAO();
    private List<ContatoCliente> contatos = new ArrayList<ContatoCliente>();
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
    private void limparCliente() {
        cadastro = new Cadastro();
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public Part getImg() {
        return img;
    }

    public void setImg(Part img) {
        this.img = img;
    }

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

    }

    public List<Cadastro> findAll(Usuario usuario) {
        findAll = cadastroDao.findAll();
        return findAll;
    }

    public String cadastroView() {
        cadastro = new Cadastro();
        return "/app/cliente/cliente?faces-redirect=true";
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

    public String cadastrar(Long id) throws IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario representante = usuarioDAO.findById(id);
        FacesContext context = FacesContext.getCurrentInstance();
        if (img != null) {
            //FacesContext facesContext = FacesContext.getCurrentInstance();
            //ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            //String path = scontext.getRealPath("/img/");
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/img/");

            img.write(path + getDateTime() + getFilename(img));
            pathImg = (getDateTime() + getFilename(img));
            cadastro.setLogomarca(pathImg);
        }
        try {
            cadastro.setRepresentante(representante);
            cadastroDao.save(cadastro);

            context.addMessage(null, new FacesMessage("Cadastrado com successo: ", "Cliente " + cadastro.getNome()));
            context.getExternalContext().getFlash().setKeepMessages(true);// faz o flash para a growl aparecer com o redirect
            cadastro = new Cadastro();

            return "/app/cliente/listacliente?faces-redirect=true";

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar! ", e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "/app/cliente/cliente?faces-redirect=true";
        }

    }
    
    public String alterarCliente(){
        cadastroDao.save(cadastro);
        return "/app/cliente/listacliente?faces-redirect=true";
    }
    
        
    public String removerCliente() {
        QuadroSWOTDAO qswot = new QuadroSWOTDAO();
        List<QuadroSWOT> q = qswot.findAllByCliente(cadastro);

        if (q.isEmpty()) {
            cadastroDao.remove(cadastro.getId());
        } else {
            qswot.remove(q.get(0).getIdquadroswot());
            cadastroDao.remove(cadastro.getId());
        }

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
        limparList();
        return "/app/cliente/propostadepedido?faces-redirect=true";
    }

    public String somaVendas(Cadastro cadastro) {
        limparList();
        return "/app/balanco/somavendas?faces-redirect=true";
    }

    public String editarBalanco(Balanco balanco) {
        limparItem();
        limparList();
        this.balanco = balanco;
        return "/app/balanco/itens?faces-redirect=true";
    }

    public String cadastrarBalanco(Produto produto) {
        balanco.setCliente(cadastro);
        itemBalanco.setProduto(produto);
        balanco.adicionarItem(itemBalanco);
        balancoDAO.save(balanco);

        cadastro = new Cadastro();

        FacesMessage msg = new FacesMessage("Novo balanço adicionado");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "123";
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
        limparItem();
    }

    public void limparItem() {
        itemBalanco = new ItemBalanco();
    }

    public void onAddNewItem() {
        // Add one new item to the table:
        ItemBalanco i = new ItemBalanco();
        i = itemBalanco;
        limparItem();
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

    public String removerItem(ItemBalanco ib) {
        itensBalanco = null;
        itensBalanco = balanco.getItemBalanco();
        int index = balanco.getItemBalanco().indexOf(ib);
        itensBalanco.remove(index);
        balanco.setItemBalanco(itensBalanco);
        balancoDAO.save(balanco);
        balancos.set(balancos.indexOf(balanco), balanco);
        cadastro.setBalanco(balancos);
        cadastroDao.save(cadastro);
        balancoDAO.findAll();
        return "/app/balanco/itens?faces-redirect=true";
    }

    public void onAddNewContato() {
        contato.setCliente(cadastro);
        contatoDAO.save(contato);
        FacesMessage msg = new FacesMessage("Novo contato adicionado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        contato = new ContatoCliente();
    }

    public void removeContato() {
        contatoDAO.remove(contato.getId());
        FacesMessage msg = new FacesMessage("Contato removido.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    
    public boolean verificaBalanco(Balanco novoBalanco) {
        for (Balanco b : balancos) {
            if (b.getPeriodo().equals(novoBalanco.getPeriodo())) {
                return true;
            }
        }
        return false;
    }

    public String cadastrarBalanco() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (verificaBalanco(balanco) == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha no cadastro.", "Balanco já cadastrado!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "/app/balanco/balanco?faces-redirect=true";
        }else{
        balanco.setCliente(cadastro);
        balanco.setItemBalanco(null);
        balancoDAO.save(balanco);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastrado com Sucesso.", "Balanco de: "+balanco.getPeriodo()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        balanco = new Balanco();

        return "/app/balanco/listarbalanco?faces-redirect=true";
        }
    }

    public String cadastroBalanco() {

        balanco = new Balanco();

        return "/app/balanco/balanco?faces-redirect=true";
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

    public Integer getSomaGeral() {
        Integer somaGeral = new Integer(0);

        for (ProdutoSuporte ps : getProdutosSuporte()) {
            somaGeral += ps.getSomaVendas();
        }

        return somaGeral;
    }

//------------- Toggler
    public List<Boolean> getList() {
        return list;
    }

    private void limparList() {
        list = new ArrayList<Boolean>();
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
        list.add(Boolean.TRUE);
    }

    public void setList(List<Boolean> list) {
        this.list = list;
    }

    public void onToggle(ToggleEvent e) {
        list.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
    }

    public ContatoCliente getContato() {
        return contato;
    }

    public void setContato(ContatoCliente contato) {
        this.contato = contato;
    }

    public List<ContatoCliente> getContatos() {
        contatos = contatoDAO.findAll(cadastro);
        return contatos;
    }

    public void setContatos(List<ContatoCliente> contatos) {
        this.contatos = contatos;
    }

    public CargoContatoCliente[] getCargoContatoCliente() {
        return CargoContatoCliente.values();
    }

}
