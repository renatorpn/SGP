/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.SGP.dao.UsuarioDAO;
import br.com.SGP.entities.Usuario;
import br.com.SGP.utils.Cargo;

/**
 *
 * @author lucas
 */
public class TesteUsuario {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        UsuarioDAO dao = new UsuarioDAO();
       user.setNome("Administrador");
       user.setNomeUsuario("Admin");
       user.setSenha("Admin");
       user.setEmail("email@email.com");
       user.setCargo(Cargo.ADM);
       //user.setId(1L);
       dao.inserirUsuario(user);      
        
        
}
}
