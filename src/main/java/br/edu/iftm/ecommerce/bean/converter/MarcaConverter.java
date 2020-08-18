/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.iftm.ecommerce.bean.converter;

import br.edu.iftm.ecommerce.entity.Marca;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Marca.class)
public class MarcaConverter implements Converter<Marca>{

    @Override
    public Marca getAsObject(FacesContext fc, UIComponent uic, String id) {
        if(id != null && !"".equals(id)){
            Marca marca = 
                    (Marca)uic.getAttributes().get("marca_"+id);
            return marca;
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Marca t) {
        if(t != null && t.getId() != null){
            uic.getAttributes().put("marca_"+t.getId(), t);
            return t.getId().toString();
        }
        return "";
    }
    
}