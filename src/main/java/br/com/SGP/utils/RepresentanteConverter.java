/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.utils;

import br.com.SGP.dao.ProdutoDAO;
import br.com.SGP.dao.UsuarioDAO;
import br.com.SGP.entities.Produto;
import br.com.SGP.entities.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas
 */
@FacesConverter(value = "representanteConverter", forClass = Usuario.class)
public class RepresentanteConverter implements javax.faces.convert.Converter {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            Long id = Long.parseLong(string);
            Usuario u = usuarioDAO.findById(id);
            return u;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && (o instanceof Usuario)) {
            return String.valueOf(((Usuario) o).getId());
        }

        return null;
    }


}
