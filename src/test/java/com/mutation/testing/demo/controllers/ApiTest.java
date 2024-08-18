package com.mutation.testing.demo.controllers;

import com.mutation.testing.demo.config.ConfigApp;
import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.enums.OrderBy;
import com.mutation.testing.demo.enums.TipoPublicacion;
import com.mutation.testing.demo.listado.ListadoClasificados;
import com.mutation.testing.demo.listado.ListadoEmprendimientos;
import com.mutation.testing.demo.listado.ListadoFactory;
import com.mutation.testing.demo.response.ListadoResponse;
import com.mutation.testing.demo.service.ServiceListado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ConfigApp.class})
@EnableConfigurationProperties
class ApiTest {

    @Autowired
    ConfigApp config;


    ServiceListado serviceListado;

    @BeforeEach
    void setUp() {
        DataSource.getInstance(config.getPath());
        ListadoClasificados clasificados = Mockito.mock(ListadoClasificados.class);
        ListadoEmprendimientos emprendimientos = Mockito.mock(ListadoEmprendimientos.class);
        Mockito.when(clasificados.getListado(Mockito.any())).thenCallRealMethod();
        Mockito.when(clasificados.armarCard(Mockito.any(),Mockito.any())).thenCallRealMethod();
        Mockito.when(emprendimientos.getListado(Mockito.any())).thenCallRealMethod();
        Mockito.when(emprendimientos.armarCard(Mockito.any(),Mockito.any())).thenCallRealMethod();
        ListadoFactory factory = new ListadoFactory(clasificados,emprendimientos);
        serviceListado = new ServiceListado(factory);
    }

    @Test
    void testConsultarListadoSimpleDevuelveTodosClasificados() {
        Api api = new Api(serviceListado);
        ListadoResponse response = api.getListado(null,null, OrderBy.DEFAULT, TipoPublicacion.CLASIFICADO);
        Assertions.assertEquals(3,response.count());
    }

    @Test
    void testConsultarFiltrandoLasPublicaciones(){
        Api api = new Api(serviceListado);
        List<Integer> publicacionesExclude = List.of(1,2,3);
        ListadoResponse response = api.getListado(publicacionesExclude,null, OrderBy.DEFAULT,TipoPublicacion.CLASIFICADO);
        Assertions.assertEquals(2,response.count());
        Assertions.assertTrue(response.cards().stream().noneMatch(c-> publicacionesExclude.contains(c.id()) ));
    }


    @Test
    void testConsultarFiltrandoAnunciantes(){
        Api api = new Api(serviceListado);
        List<String> anunciantesExclude = List.of("MagnetoProp");
        ListadoResponse response = api.getListado(null,anunciantesExclude, OrderBy.DEFAULT,TipoPublicacion.CLASIFICADO);
        Assertions.assertEquals(2,response.count());
        Assertions.assertTrue(response.cards().stream().noneMatch(c-> anunciantesExclude.contains(c.anunciante())));
    }

    @Test
    void testConsultarOrdenandoPorPrecio(){
        Api api = new Api(serviceListado);
        ListadoResponse response = api.getListado(null,null, OrderBy.PRECIO,TipoPublicacion.CLASIFICADO);
        Assertions.assertEquals(7,response.cards().get(0).id());
    }

    @Test
    void testConsultarOrdenandoPorNivel(){
        Api api = new Api(serviceListado);
        ListadoResponse response = api.getListado(null,null, OrderBy.NIVEL,TipoPublicacion.CLASIFICADO);
        Assertions.assertEquals(7,response.cards().get(0).id());
    }

    @Test
    void testConsultarOrdenandoPorXfactor(){
        Api api = new Api(serviceListado);
        ListadoResponse response = api.getListado(null,null, OrderBy.XFACTOR,TipoPublicacion.CLASIFICADO);
        Assertions.assertEquals(1,response.cards().get(0).id());
    }

}