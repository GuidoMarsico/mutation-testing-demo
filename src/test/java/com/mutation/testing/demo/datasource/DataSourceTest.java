package com.mutation.testing.demo.datasource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class DataSourceTest {

        @Test
        void testElDsTieneQueLevantarLosDatosDePrueba(){
            DataSource ds = new DataSource("src/test/resources/test.csv");
            Assertions.assertFalse(ds.publicacionList.isEmpty());
        }





}