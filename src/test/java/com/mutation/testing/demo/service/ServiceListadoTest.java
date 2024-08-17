package com.mutation.testing.demo.service;

import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.enums.OrderBy;
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
        DataSource.getInstance("/home/gmarsico/Documentos/mio/mt/demo/src/test/resources/test.csv");
        this.serviceListado = new ServiceListado();
    }

    @Test
    void testDebeDevolverListadosDeCards(){
        Assertions.assertFalse(serviceListado.armarListado(Optional.empty(),Optional.empty(), OrderBy.DEFAULT).isEmpty());
    }


    @Test
    void testDebeDevolverListadoExcluyendoCiertasPublicaciones(){
        List<Integer> idPublicacionesExclude = List.of(1,3);
        List<Card> listado = serviceListado.armarListado(Optional.of(idPublicacionesExclude),Optional.empty(),OrderBy.DEFAULT);
        Assertions.assertTrue(listado.stream().noneMatch(card -> idPublicacionesExclude.contains(card.id())));
    }

    @Test
    void testDevolverListadoExcluyendoCiertosAnunciantes(){
        List<String> anunciantes = List.of("HermandadProp");
        List<Card> listado = serviceListado.armarListado(Optional.empty(),Optional.of(anunciantes),OrderBy.DEFAULT);
        Assertions.assertTrue(listado.stream().noneMatch(card -> anunciantes.contains(card.anunciante())));
    }

    @Test
    void testDevolverListadoOrdenadoPorPrecio(){
        List<Card> listado =  serviceListado.armarListado(Optional.empty(),Optional.empty(), OrderBy.PRECIO);
        Card primero = listado.get(0);
        Assertions.assertEquals(4,primero.id());
    }


    @Test
    void testDevolverListadoOrdenadoPorNivel(){
        List<Card> listado =  serviceListado.armarListado(Optional.empty(),Optional.empty(), OrderBy.NIVEL);
        Card primero = listado.get(0);
        Assertions.assertEquals(7,primero.id());
    }

    @Test
    void testDevolverListadoOrdenadoPorXFactor(){
        List<Card> listado =  serviceListado.armarListado(Optional.empty(),Optional.empty(), OrderBy.XFACTOR);
        Card primero = listado.get(0);
        Assertions.assertEquals(5,primero.id());
    }

}