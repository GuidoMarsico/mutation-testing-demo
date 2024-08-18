package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.datasource.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListadoClasificadosTest {

    @Test
    void testObtenerListadoDeLosClasificados() {
        DataSource ds = DataSource.getInstance("src/test/resources/test.csv");
        ListadoClasificados clasificados = new ListadoClasificados();
        Assertions.assertEquals(3,clasificados.getListado(ds).size());
    }

}