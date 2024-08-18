package com.mutation.testing.demo.service;

import com.mutation.testing.demo.comparator.NivelComparator;
import com.mutation.testing.demo.comparator.PriceComparator;
import com.mutation.testing.demo.comparator.XFactorComparator;
import com.mutation.testing.demo.controllers.SearchParams;
import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.enums.OrderBy;
import com.mutation.testing.demo.enums.TipoPublicacion;
import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import com.mutation.testing.demo.util.Util;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ServiceListado {

    DataSource ds;


    public ServiceListado() {
        this.ds = DataSource.getInstance("");
    }

    public List<Card> armarListado(SearchParams searchParams){
        List<Card> listado = new ArrayList<>();

        for(Publicacion p : ds.publicacionList) {
            if (publicationPassFilter(searchParams.publicacionesToExclude(), searchParams.anunciantesToExclude(), p)) {

                String tipoPropiedad = Util.getTipoPropiedadById(p.idTipoDePropiedad()).nombre;
                String nivel = Util.getNivelById(p.idNivel()).nombre;
                String xFactor = Util.getXfactor(p.xFactor()).xFactor;

                if(searchParams.tipoPublicacion().equals(TipoPublicacion.CLASIFICADO)){
                    if(List.of(1,2).contains(p.idTipoDePropiedad()) & p.idPublicacionPadre() == null)
                        listado.add(new Card(p.id(), tipoPropiedad, p.anunciante(), p.ubicacion(), p.precio(), p.fechaPublicacion(), nivel, xFactor));
                }

                if(searchParams.tipoPublicacion().equals(TipoPublicacion.EMPRENDIMIENTO)){
                    if((p.idTipoDePropiedad().equals(10)) || (List.of(1,2).contains(p.idTipoDePropiedad()) & p.idPublicacionPadre() != null))
                        listado.add(new Card(p.id(), tipoPropiedad, p.anunciante(), p.ubicacion(), p.precio(), p.fechaPublicacion(), nivel, xFactor));
                }

            }
        }


            if(searchParams.order().equals(OrderBy.PRECIO))
                listado= listado.stream().sorted(new PriceComparator()).collect(Collectors.toList());
            if(searchParams.order().equals(OrderBy.NIVEL))
                listado= listado.stream().sorted(new NivelComparator()).collect(Collectors.toList());
            if(searchParams.order().equals(OrderBy.XFACTOR))
                listado = listado.stream().sorted(new XFactorComparator()).collect(Collectors.toList());

        return listado;
    }

    private  boolean publicationPassFilter(Optional<List<Integer>> publicacionesExclude, Optional<List<String>> anunciantesExclude, Publicacion p) {
        Boolean pass = Boolean.TRUE;
        if(publicacionesExclude.isPresent())
            pass = pass & !publicacionesExclude.get().contains(p.id());

        if(anunciantesExclude.isPresent())
            pass = pass & !anunciantesExclude.get().contains(p.anunciante());

        return pass;
    }

}
