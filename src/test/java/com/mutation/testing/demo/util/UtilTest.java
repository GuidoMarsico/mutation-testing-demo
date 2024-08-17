package com.mutation.testing.demo.util;


import com.mutation.testing.demo.enums.Nivel;
import com.mutation.testing.demo.enums.TipoPropiedad;
import com.mutation.testing.demo.model.Publicacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class UtilTest {

    @Test
    void testTieneQueLevantarDatosDelCSVDeEjemplo() throws FileNotFoundException {
        List<List<String>> record =  Util.read("src/test/resources/test.csv");
        Assertions.assertFalse(record.isEmpty());
    }

    @Test
    void testArchivoNoExisteLanzaEx(){
        Assertions.assertThrows(RuntimeException.class, () ->  Util.read("blah.csv"));
    }


    @Test
    void transformRowCasaNivelGratisDeXavierProp() {
        List<String> row = new ArrayList<>();
        row.add("1");
        row.add("");
        row.add("1");
        row.add("XaxierProp");
        row.add("Calle Falsa 1234");
        row.add("999");
        row.add("1");
        row.add("10");
        row.add("31-12-1999");

        Publicacion p = Util.transform(row);
        Assertions.assertEquals(TipoPropiedad.CASA.id,p.idTipoDePropiedad());
        Assertions.assertEquals(Nivel.GRATIS.id,p.idNivel());
        Assertions.assertEquals("XaxierProp",p.anunciante());

    }
}