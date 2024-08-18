package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import com.mutation.testing.demo.util.Util;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("clasificados")
public class ListadoClasificados implements Listado {




    @Override
    public List<Card> getListado(DataSource ds) {
        List<Card> clasificados = new ArrayList<>();
        for(Publicacion p : ds.publicacionList){
            if(List.of(1,2).contains(p.idTipoDePropiedad()) & p.idPublicacionPadre() == null){
                String tipoPropiedad = Util.getTipoPropiedadById(p.idTipoDePropiedad()).nombre;
                String nivel = Util.getNivelById(p.idNivel()).nombre;
                String xFactor = Util.getXfactor(p.xFactor()).xFactor;
                clasificados.add(new Card(p.id(), tipoPropiedad, p.anunciante(), p.ubicacion(), p.precio(), p.fechaPublicacion(), nivel, xFactor,"clasificado"));
            }
        }

        return clasificados;
    }
}
