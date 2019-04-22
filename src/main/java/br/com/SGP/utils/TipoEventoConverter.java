/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.utils;

import br.com.SGP.dao.TipoEventoDAO;
import br.com.SGP.entities.TipoEvento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lucas
 */
@FacesConverter(value = "tipoEventoConverter", forClass = TipoEvento.class)
public class TipoEventoConverter implements javax.faces.convert.Converter {
    TipoEventoDAO tipoEventoDAO = new TipoEventoDAO();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            Long id = Long.parseLong(string);
            TipoEvento p = tipoEventoDAO.findById(id);
            return p;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null && (o instanceof TipoEvento)) {
            return String.valueOf(((TipoEvento) o).getIdTipoEvento());
        }

        return null;
    }


}
