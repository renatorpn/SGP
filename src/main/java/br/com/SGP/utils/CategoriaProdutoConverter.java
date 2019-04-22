/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.utils;

import br.com.SGP.dao.CategoriaProdutoDAO;
import br.com.SGP.entities.CategoriaProduto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas
 */
@FacesConverter(value = "categoriaConverter", forClass = CategoriaProduto.class)
public class CategoriaProdutoConverter implements javax.faces.convert.Converter {
    CategoriaProdutoDAO categoriaDAO = new CategoriaProdutoDAO();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            Long id = Long.parseLong(string);
            CategoriaProduto c = categoriaDAO.findById(id);
            return c;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && (o instanceof CategoriaProduto)) {
            return String.valueOf(((CategoriaProduto) o).getIdcategoria());
        }

        return null;
    }


}
