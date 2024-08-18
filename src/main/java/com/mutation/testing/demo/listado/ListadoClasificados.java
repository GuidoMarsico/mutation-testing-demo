package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("clasificado")
public class ListadoClasificados implements Listado {




    @Override
    public List<Card> getListado(DataSource ds) {
        List<Card> clasificados = new ArrayList<>();
        for(Publicacion p : ds.publicacionList){
            if(List.of(1,2).contains(p.idTipoDePropiedad()) & p.idPublicacionPadre() == null){
                clasificados.add(armarCard(p,"clasificado", Optional.empty()));
            }
        }

        return clasificados;
    }
}
