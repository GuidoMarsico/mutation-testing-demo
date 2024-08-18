package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import com.mutation.testing.demo.util.Util;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("emprendimientos")
public class ListadoEmprendimientos implements Listado {

    @Override
    public List<Card> getListado(DataSource ds) {
        List<Card> listado = new ArrayList<>();
        for(Publicacion p : ds.publicacionList){
            if((p.idTipoDePropiedad().equals(10)) || (List.of(1,2).contains(p.idTipoDePropiedad()) & p.idPublicacionPadre() != null)){
                String tipoPublicacion = p.idTipoDePropiedad().equals(10) ? "emprendimiento" : "unidad";
                listado.add(armarCard(p,tipoPublicacion));
            }
        }

        return listado;
    }
}
