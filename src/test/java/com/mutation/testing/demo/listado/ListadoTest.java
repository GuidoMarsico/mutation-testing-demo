package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListadoTest {

    @Test
    void armarCard() {
        Publicacion publicacion = new Publicacion(1,2,2,
                "Trusk","Pentagono al 500",100,2,49,"20-10-2023");
        Listado listado = new ListadoEmprendimientos();
        Card card = listado.armarCard(publicacion,"clasificado");
        Assertions.assertNotNull(card);
    }
}