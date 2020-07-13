/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.bean.converter;

import br.edu.iftm.ecommerce.entity.Categoria;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter<Categoria>{

    @Override
    public Categoria getAsObject(FacesContext fc, UIComponent uic, String id) {
        if(id != null && !"".equals(id)){
            Categoria categoria = 
                    (Categoria)uic.getAttributes().get("categoria_"+id);
            return categoria;
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Categoria t) {
        if(t != null && t.getId() != null){
            uic.getAttributes().put("categoria_"+t.getId(), t);
            return t.getId().toString();
        }
        return "";
    }
    
}