package com.mutation.testing.demo.service;

import com.mutation.testing.demo.controllers.SearchParams;
import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.enums.OrderBy;
import com.mutation.testing.demo.enums.TipoPublicacion;
import com.mutation.testing.demo.listado.ListadoClasificados;
import com.mutation.testing.demo.listado.ListadoEmprendimientos;
import com.mutation.testing.demo.listado.ListadoFactory;
import com.mutation.testing.demo.response.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

class ServiceListadoTest {

    ServiceListado serviceListado;

    @BeforeEach
    void setUp() {
        DataSource.getInstance("src/test/resources/test.csv");
        ListadoClasificados clasificados = Mockito.mock(ListadoClasificados.class);
        ListadoEmprendimientos emprendimientos = Mockito.mock(ListadoEmprendimientos.class);
        Mockito.when(clasificados.getListado(Mockito.any())).thenCallRealMethod();
        Mockito.when(clasificados.armarCard(Mockito.any(),Mockito.any(),Mockito.any())).thenCallRealMethod();
        Mockito.when(emprendimientos.getListado(Mockito.any())).thenCallRealMethod();
        Mockito.when(emprendimientos.armarCard(Mockito.any(),Mockito.any(),Mockito.any())).thenCallRealMethod();
        ListadoFactory factory = new ListadoFactory(clasificados,emprendimientos);
        this.serviceListado = new ServiceListado(factory);
    }

    @Test
    void testDebeDevolverListadosDeClasificados(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.DEFAULT, TipoPublicacion.CLASIFICADO);
        Assertions.assertEquals(3,serviceListado.armarListado(searchParams).size());
        Assertions.assertTrue(serviceListado.armarListado(searchParams).stream().allMatch(card -> card.tipoPublicacion().equals("clasificado")));
    }

    @Test
    void testDebeDevolverListadosDeEmprendimientosConSusUnidades(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.DEFAULT, TipoPublicacion.EMPRENDIMIENTO);
        Assertions.assertEquals(1,serviceListado.armarListado(searchParams).size());
        Assertions.assertEquals(1,serviceListado.armarListado(searchParams).stream().filter(card -> card.tipoPublicacion().equals("emprendimiento")).count());
        Assertions.assertEquals(3,serviceListado.armarListado(searchParams).stream().filter(card -> card.tipoPublicacion().equals("emprendimiento")).findFirst().get().unidad().size());

    }


    @Test
    void testDebeDevolverListadoExcluyendoCiertasPublicaciones(){
        List<Integer> idPublicacionesExclude = List.of(1,3);
        SearchParams searchParams = new SearchParams(Optional.of(idPublicacionesExclude),Optional.empty(),OrderBy.DEFAULT,TipoPublicacion.CLASIFICADO);
        List<Card> listado = serviceListado.armarListado(searchParams);
        Assertions.assertTrue(listado.stream().noneMatch(card -> idPublicacionesExclude.contains(card.id())));
    }

    @Test
    void testDevolverListadoExcluyendoCiertosAnunciantes(){
        List<String> anunciantes = List.of("HermandadProp");
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.of(anunciantes),OrderBy.DEFAULT,TipoPublicacion.CLASIFICADO);
        List<Card> listado = serviceListado.armarListado(searchParams);
        Assertions.assertTrue(listado.stream().noneMatch(card -> anunciantes.contains(card.anunciante())));
    }

    @Test
    void testDevolverListadoOrdenadoPorPrecio(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.PRECIO,TipoPublicacion.CLASIFICADO);
        List<Card> listado =  serviceListado.armarListado(searchParams);
        Card primero = listado.get(0);
        Assertions.assertEquals(7,primero.id());
    }


    @Test
    void testDevolverListadoOrdenadoPorNivel(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.NIVEL,TipoPublicacion.CLASIFICADO);
        List<Card> listado =  serviceListado.armarListado(searchParams);
        Card primero = listado.get(0);
        Assertions.assertEquals(7,primero.id());
    }

    @Test
    void testDevolverListadoOrdenadoPorXFactor(){
        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.XFACTOR,TipoPublicacion.CLASIFICADO);
        List<Card> listado =  serviceListado.armarListado(searchParams);
        Card primero = listado.get(0);
        Assertions.assertEquals(1,primero.id());
    }

}