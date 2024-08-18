package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.datasource.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListadoEmprendimientosTest {

    @Test
    void testObtenerListadoDeLosEmprendimientos() {
        DataSource ds = DataSource.getInstance("src/test/resources/test.csv");
        ListadoEmprendimientos emprendimientos = new ListadoEmprendimientos();
        Assertions.assertEquals(4,emprendimientos.getListado(ds).size());
    }
}