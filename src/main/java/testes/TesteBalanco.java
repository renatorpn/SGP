/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.SGP.bean.CadastroBean;
import br.com.SGP.dao.BalancoDAO;
import br.com.SGP.dao.CadastroDao;
import br.com.SGP.entities.Balanco;
import br.com.SGP.entities.Cadastro;

/**
 *
 * @author lucas
 */
public class TesteBalanco {
    public static void main(String[] args) {
        CadastroDao dao = new CadastroDao();
        BalancoDAO bdao = new BalancoDAO();
        Cadastro c = dao.findById(1L);
        Balanco b = new Balanco();
        b.setCliente(c);
        bdao.save(b);
        
        
    }
}
