package br.edu.iftm.ecommerce.bean;

import java.util.Calendar;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Teste {
    
    public void testar() {
        long time = Calendar.getInstance().getTimeInMillis()+4000;
        long agora = Calendar.getInstance().getTimeInMillis();
        while(time > agora) {
            agora = Calendar.getInstance().getTimeInMillis();
        }
    }
    
}
