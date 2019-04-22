/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.SGP.dao.BalancoDAO;
import br.com.SGP.dao.CadastroDao;
import br.com.SGP.dao.ProdutoDAO;
import br.com.SGP.entities.Balanco;
import br.com.SGP.entities.Cadastro;
import br.com.SGP.entities.ItemBalanco;
import br.com.SGP.entities.Produto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import jdk.nashorn.internal.objects.Global;

/**
 *
 * @author lucas
 */
public class TesteCliente {
    public static void main(String[] args) {
        Date d = new Date();
        
        CadastroDao cadastroDAO = new CadastroDao();
        Cadastro c = new Cadastro();
        c = cadastroDAO.findById(1L);
        Produto p = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        p = produtoDAO.findById(1L);
        
        
        Balanco b = new Balanco();
        
        BalancoDAO dao = new BalancoDAO();
        b = dao.findById(1L);
        ItemBalanco item = new ItemBalanco(p);
        
        List <ItemBalanco> l = new ArrayList<>();
        
        b.setCliente(c);
        b.setPeriodo("Dezembro 2018");
        item.setIdBalanco(b);
        item.setQtdVenda(17);
        item.setQtdEstoque(133);
        item.setIdBalanco(b);
        l.add(item);
        b.setItemBalanco(l);
        
        dao.save(b);
        
        
        }
}
