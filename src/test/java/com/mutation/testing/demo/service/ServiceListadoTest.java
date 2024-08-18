package com.mutation.testing.demo.service;

import com.mutation.testing.demo.controllers.SearchParams;
import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.enums.OrderBy;
import com.mutation.testing.demo.enums.TipoPublicacion;
import com.mutation.testing.demo.response.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


class ServiceListadoTest {

    ServiceListado serviceListado;

    @BeforeEach
    void setUp() {
        DataSource.getInstance("src/test/resources/test.csv");
        this.serviceListado = new ServiceListado();
    }

    @Test
    void testDebeDevolverListadosDeClasificados(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.DEFAULT, TipoPublicacion.CLASIFICADO);
        Assertions.assertEquals(3,serviceListado.armarListado(searchParams).size());
    }

    @Test
    void testDebeDevolverListadosDeEmprendimientos(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.DEFAULT, TipoPublicacion.EMPRENDIMIENTO);
        Assertions.assertEquals(4,serviceListado.armarListado(searchParams).size());
    }


    @Test
    void testDebeDevolverListadoExcluyendoCiertasPublicaciones(){
        List<Integer> idPublicacionesExclude = List.of(1,3);
        SearchParams searchParams = new SearchParams(Optional.of(idPublicacionesExclude),Optional.empty(),OrderBy.DEFAULT,null);
        List<Card> listado = serviceListado.armarListado(searchParams);
        Assertions.assertTrue(listado.stream().noneMatch(card -> idPublicacionesExclude.contains(card.id())));
    }

    @Test
    void testDevolverListadoExcluyendoCiertosAnunciantes(){
        List<String> anunciantes = List.of("HermandadProp");
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.of(anunciantes),OrderBy.DEFAULT,null);
        List<Card> listado = serviceListado.armarListado(searchParams);
        Assertions.assertTrue(listado.stream().noneMatch(card -> anunciantes.contains(card.anunciante())));
    }

    @Test
    void testDevolverListadoOrdenadoPorPrecio(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.PRECIO,null);
        List<Card> listado =  serviceListado.armarListado(searchParams);
        Card primero = listado.get(0);
        Assertions.assertEquals(4,primero.id());
    }


    @Test
    void testDevolverListadoOrdenadoPorNivel(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.NIVEL,null);
        List<Card> listado =  serviceListado.armarListado(searchParams);
        Card primero = listado.get(0);
        Assertions.assertEquals(7,primero.id());
    }

    @Test
    void testDevolverListadoOrdenadoPorXFactor(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.XFACTOR,null);
        List<Card> listado =  serviceListado.armarListado(searchParams);
        Card primero = listado.get(0);
        Assertions.assertEquals(5,primero.id());
    }

}