package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("emprendimiento")
public class ListadoEmprendimientos implements Listado {

    @Override
    public List<Card> getListado(DataSource ds) {
        List<Card> listado = new ArrayList<>();
        List<Publicacion> publicacionList = ds.publicacionList;
        List<Publicacion> unidades = ListadoHelper.getUnidades(publicacionList);
        List<Publicacion> emprendimientos = ListadoHelper.getEmprendimientos(publicacionList);
        for(Publicacion p : emprendimientos){
              Optional<List<Card>> cardUnidades = getUnidadesDeEmprendimiento(p.anunciante(),unidades);
              listado.add(armarCard(p,"emprendimiento", cardUnidades));
            }
        return listado;
    }

    private Optional<List<Card>> getUnidadesDeEmprendimiento(String anunciante, List<Publicacion> unidades) {
        List<Card> unidadesCard = new ArrayList<>();
        List<Publicacion> unidadesAnunciante = unidades.stream().filter(publicacion -> publicacion.anunciante().equals(anunciante)).toList();
        for(Publicacion unidadAnunciante : unidadesAnunciante){
            unidadesCard.add(armarCard(unidadAnunciante,"unidad",Optional.empty()));
        }
        return Optional.of(unidadesCard);
    }
}
