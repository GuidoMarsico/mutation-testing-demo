package com.mutation.testing.demo.service;

import com.mutation.testing.demo.comparator.NivelComparator;
import com.mutation.testing.demo.comparator.PriceComparator;
import com.mutation.testing.demo.comparator.XFactorComparator;
import com.mutation.testing.demo.controllers.SearchParams;
import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.enums.OrderBy;
import com.mutation.testing.demo.enums.TipoPublicacion;
import com.mutation.testing.demo.listado.ListadoFactory;
import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import com.mutation.testing.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ServiceListado {

    DataSource ds;
    ListadoFactory factory;

    @Autowired
    public ServiceListado(ListadoFactory factory) {
        this.factory = factory;
        this.ds = DataSource.getInstance("");
    }

    public List<Card> armarListado(SearchParams searchParams){
            List<Card> listado = factory.getListado(searchParams.tipoPublicacion()).
                    getListado(ds).stream().
                    filter(card -> publicationPassFilter(searchParams.publicacionesToExclude(),searchParams.anunciantesToExclude(),card))
                    .toList();

            if(searchParams.order().equals(OrderBy.PRECIO))
                listado= listado.stream().sorted(new PriceComparator()).collect(Collectors.toList());
            if(searchParams.order().equals(OrderBy.NIVEL))
                listado= listado.stream().sorted(new NivelComparator()).collect(Collectors.toList());
            if(searchParams.order().equals(OrderBy.XFACTOR))
                listado = listado.stream().sorted(new XFactorComparator()).collect(Collectors.toList());

        return listado;
    }

    private  boolean publicationPassFilter(Optional<List<Integer>> publicacionesExclude, Optional<List<String>> anunciantesExclude, Card card) {
        Boolean pass = Boolean.TRUE;
        if(publicacionesExclude.isPresent())
            pass = pass & !publicacionesExclude.get().contains(card.id());

        if(anunciantesExclude.isPresent())
            pass = pass & !anunciantesExclude.get().contains(card.anunciante());

        return pass;
    }

}
