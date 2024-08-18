package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.enums.TipoPublicacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ListadoFactoryTest {

    @Test
    void testObtenerInstanciaDeListadoClasificados(){
        ListadoFactory factory = new ListadoFactory(new ListadoClasificados(), new ListadoEmprendimientos());
        Assertions.assertInstanceOf(ListadoClasificados.class,factory.getListado(TipoPublicacion.CLASIFICADO));

    }

    @Test
    void testObtenerInstanciaDeListadoEmprendimientos(){
        ListadoFactory factory = new ListadoFactory(new ListadoClasificados(), new ListadoEmprendimientos());
        Assertions.assertInstanceOf(ListadoEmprendimientos.class,factory.getListado(TipoPublicacion.EMPRENDIMIENTO));

    }


}