/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.utils;

import br.com.SGP.dao.CadastroDao;
import br.com.SGP.entities.Cadastro;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas
 */
@FacesConverter(value = "clienteConverter", forClass = Cadastro.class)
public class ClienteConverter implements javax.faces.convert.Converter {
    CadastroDao cadastroDAO = new CadastroDao();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            Long id = Long.parseLong(string);
            Cadastro p = cadastroDAO.findById(id);
            return p;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && (o instanceof Cadastro)) {
            return String.valueOf(((Cadastro) o).getId());
        }

        return null;
    }


}
