/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.SGP.dao.CadastroDao;
import br.com.SGP.dao.CategoriaProdutoDAO;
import br.com.SGP.dao.ProdutoDAO;
import br.com.SGP.dao.QuadroSWOTDAO;
import br.com.SGP.dao.TipoEventoDAO;
import br.com.SGP.dao.UsuarioDAO;
import br.com.SGP.entities.Cadastro;
import br.com.SGP.entities.CategoriaProduto;
import br.com.SGP.entities.Produto;
import br.com.SGP.entities.QuadroSWOT;
import br.com.SGP.entities.TipoEvento;
import br.com.SGP.entities.Usuario;
import br.com.SGP.utils.AmbienteVendasCliente;
import br.com.SGP.utils.CanalVendasCliente;
import br.com.SGP.utils.Cargo;
import br.com.SGP.utils.CategoriaCliente;
import br.com.SGP.utils.ClassificacaoClienteABC;
import br.com.SGP.utils.CorProduto;
import br.com.SGP.utils.Estado;
import br.com.SGP.utils.FxClassificacao;
import br.com.SGP.utils.Sexo;

/**
 *
 * @author renas
 */
public class TesteAddAll {
    public static void main(String[] args) {
        
        //==========================================
        //============= Usuario ADD ================
        //==========================================
        Usuario user = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        user.setNome("Administrador");
        user.setNomeUsuario("Admin");
        user.setSenha("Admin");
        user.setEmail("email@email.com");
        user.setCargo(Cargo.ADM);
        //user.setId(1L);
        usuarioDAO.inserirUsuario(user);      
        //=========================================
       
       
        //==========================================
        //============= Cliente ADD ================
        //==========================================
        Cadastro cadastro = new Cadastro();
        CadastroDao cadastroDAO = new CadastroDao();
        cadastro.setRepresentante(user);
        cadastro.setRazaoSocial("Lojas Testes LTDA");
        cadastro.setCnpj("60.009.457/0001-99");
        cadastro.setNome("Testes Calçados");
        cadastro.setQuantidadelojas(1);
        cadastro.setEstado(Estado.RJ);
        cadastro.setCidade("Rio de Janeiro");
        cadastro.setLogradouro("Rua dos Tolos");
        cadastro.setBairro("Campo Grande");
        cadastro.setCep("20241060");
        cadastro.setNumero("123");
        cadastro.setCategoriaCliente(CategoriaCliente.DIAMANTE);
        cadastro.setAmbienteVendasCliente(AmbienteVendasCliente.VIRTUAL);
        cadastro.setComplemento("Apt 110");
        cadastro.setCanalVendasCliente(CanalVendasCliente.MAGAZINE);
        cadastro.setClassificacaoabc(ClassificacaoClienteABC.TOP);
        cadastro.setOutrasInformacoes("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        cadastroDAO.save(cadastro);
        //=========================================
    
        
        //==========================================
        //========= Categoria Produto ADD ==========
        //==========================================
        CategoriaProduto categoriaProduto = new CategoriaProduto();
        CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();
        categoriaProduto.setNomeCategoria("Fitness");
        categoriaProduto.setDescricao("Detalhes em macadâmia");
        categoriaProdutoDAO.save(categoriaProduto);
        //==========================================


        //==========================================
        //============= Produto ADD ================
        //==========================================
        Produto produto = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produto.setNome("The Shortstop");
        produto.setCodigo("123456");
        produto.setCor(CorProduto.ROSA);
        produto.setFxclassificacao(FxClassificacao.P1);
        produto.setPrecovenda(166.90);
        produto.setGenero(Sexo.FEMININO);
        produto.setCategoriaProduto(categoriaProduto);
        produtoDAO.save(produto);
        //==========================================
        
        //==========================================
        //============ Quadro SWOT ADD =============
        //==========================================
        QuadroSWOT quadroSWOT = new QuadroSWOT();
        QuadroSWOTDAO quadroSWOTDAO = new QuadroSWOTDAO();
        quadroSWOT.setCliente(cadastro);
        quadroSWOT.setAmeacas("Lorem ipsum");
        quadroSWOT.setOportunidades("consectetur adipiscing elit");
        quadroSWOT.setPontosFortes("sed do eiusmod tempor incididunt");
        quadroSWOT.setPontosFracos("Ut enim ad minim veniam, quis nostrud exercitation");
        quadroSWOTDAO.save(quadroSWOT);
                
        //==========================================
        
        
        //==========================================
        //============ Tipo Evento ADD =============
        //==========================================
        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setDescricao("Ação de Vendas");
        TipoEventoDAO tipoEventoDAO = new TipoEventoDAO();
        tipoEventoDAO.save(tipoEvento);
        
        //==========================================
    }
}
