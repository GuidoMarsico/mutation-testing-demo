package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.enums.TipoPublicacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ListadoFactory.class, ListadoClasificados.class,ListadoEmprendimientos.class})
class ListadoFactoryTest {

    @Autowired
    ListadoFactory factory;


    @Test
    void testObtenerInstanciaDeListadoClasificados(){
        Assertions.assertInstanceOf(ListadoClasificados.class,factory.getListado(TipoPublicacion.CLASIFICADO));

    }

    @Test
    void testObtenerInstanciaDeListadoEmprendimientos(){
        Assertions.assertInstanceOf(ListadoEmprendimientos.class,factory.getListado(TipoPublicacion.EMPRENDIMIENTO));

    }


}