/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.SGP.bean.CadastroBean;
import br.com.SGP.dao.BalancoDAO;
import br.com.SGP.dao.CadastroDao;
import br.com.SGP.dao.QuadroSWOTDAO;
import br.com.SGP.entities.Balanco;
import br.com.SGP.entities.Cadastro;
import br.com.SGP.entities.QuadroSWOT;

/**
 *
 * @author lucas
 */
public class TesteBalanco {
    public static void main(String[] args) {
        CadastroDao clidao = new CadastroDao();
        Cadastro cli = clidao.findById(1L);
        QuadroSWOT quadroSWOT = new QuadroSWOT();
        QuadroSWOTDAO quadroSWOTDAO = new QuadroSWOTDAO();
        quadroSWOT.setCliente(cli);
        quadroSWOT.setAmeacas("Lorem ipsum");
        quadroSWOT.setOportunidades("consectetur adipiscing elit");
        quadroSWOT.setPontosFortes("sed do eiusmod tempor incididunt");
        quadroSWOT.setPontosFracos("Ut enim ad minim veniam, quis nostrud exercitation");
        quadroSWOTDAO.save(quadroSWOT);
        
        
    }
}
