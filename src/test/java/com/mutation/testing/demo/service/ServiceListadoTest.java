package com.mutation.testing.demo.service;

import com.mutation.testing.demo.controllers.SearchParams;
import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.enums.*;
import com.mutation.testing.demo.listado.Listado;
import com.mutation.testing.demo.listado.ListadoClasificados;
import com.mutation.testing.demo.listado.ListadoEmprendimientos;
import com.mutation.testing.demo.listado.ListadoFactory;
import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ServiceListadoTest {

    ServiceListado serviceListado;
    ListadoEmprendimientos emprendimientos;

    @BeforeEach
    void setUp() {
        DataSource.getInstance("src/test/resources/test.csv");
        ListadoClasificados clasificados = Mockito.mock(ListadoClasificados.class);
        emprendimientos = Mockito.mock(ListadoEmprendimientos.class);
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


    @Test
    void testDevolverListadoOrdenadoPorCantidadUnidades(){
        ListadoEmprendimientos listadoEmprendimientosMock = Mockito.mock();
        ListadoFactory listadoFactoryMock = Mockito.mock();
        Mockito.when(listadoFactoryMock.getListado(Mockito.any())).thenReturn(listadoEmprendimientosMock);
        ServiceListado serviceOrderUnidad = new ServiceListado(listadoFactoryMock);

        List<Card> primerMockUnida = Mockito.mock();
        Mockito.when(primerMockUnida.size()).thenReturn(10);
        List<Card> segundoMockUnida = Mockito.mock();
        Mockito.when(segundoMockUnida.size()).thenReturn(2);
        List<Card> tercerMockUnida = Mockito.mock();
        Mockito.when(tercerMockUnida.size()).thenReturn(30);

        List<Card> cardsFake = new ArrayList<>();
        cardsFake.add(new Card(1, TipoPropiedad.DESARROLLO.nombre, "Don Raul Prop", "Almagro" , null , "20-10-2023", Nivel.NORMAL_DESA.nombre, XFactor.XGOLD.xFactor, "emprendimiento",primerMockUnida ));
        cardsFake.add(new Card(5, TipoPropiedad.DESARROLLO.nombre, "Minguito Prop", "Caballito" , null , "10-10-2024", Nivel.EXCELENTE_DESA.nombre,  XFactor.XBLUE.xFactor, "emprendimiento",segundoMockUnida ));
        cardsFake.add(new Card(10, TipoPropiedad.DESARROLLO.nombre, "X-Prop", "New York" , null , "15-02-1997", Nivel.EXCELENTE_DESA.nombre, XFactor.XRED.xFactor, "emprendimiento",tercerMockUnida ));

        Mockito.when(listadoEmprendimientosMock.getListado(Mockito.any())).thenReturn(cardsFake);

        SearchParams searchParams = new SearchParams(Optional.empty(),Optional.empty(), OrderBy.CANTUNIDADES,TipoPublicacion.EMPRENDIMIENTO);

        List<Card> listadoOrdenado =  serviceOrderUnidad.armarListado(searchParams);
        Card primero = listadoOrdenado.get(0);
        Assertions.assertEquals(10,primero.id());
    }

}