/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.utils;

import br.com.SGP.dao.ProdutoDAO;
import br.com.SGP.entities.Produto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas
 */
@FacesConverter(value = "produtoConverter", forClass = Produto.class)
public class ProdutoConverter implements javax.faces.convert.Converter {
    ProdutoDAO produtoDAO = new ProdutoDAO();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            Long id = Long.parseLong(string);
            Produto p = produtoDAO.findById(id);
            return p;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && (o instanceof Produto)) {
            return String.valueOf(((Produto) o).getIdproduto());
        }

        return null;
    }


}
