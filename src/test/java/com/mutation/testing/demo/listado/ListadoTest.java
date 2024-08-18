package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ListadoTest {

    @Test
    void armarCard() {
        Publicacion publicacion = new Publicacion(1,null,2,
                "Trusk","Pentagono al 500",100,2,49,"20-10-2023");
        Listado listado = new ListadoClasificados();
        Card card = listado.armarCard(publicacion,"clasificado", Optional.empty());
        Assertions.assertNotNull(card);
    }

    @Test
    void armarCardEmprendimientoTresUnidades(){
        Publicacion publicacion = new Publicacion(1,2,10,
                "Trusk","Pentagono al 500",100,2,49,"20-10-2023");
        List<Card> unidades = Mockito.mock();
        Mockito.when(unidades.size()).thenReturn(3);

        Listado listado = new ListadoClasificados();
        Card card = listado.armarCard(publicacion,"emprendimiento",Optional.of(unidades));
        Assertions.assertEquals(3,card.unidad().size());
    }

    @Test
    void ArmarCardClasificadoUnidadesZero(){
        Publicacion publicacion = new Publicacion(1,null,2,
                "Trusk","Pentagono al 500",100,2,49,"20-10-2023");
        Listado listado = new ListadoClasificados();
        Card card = listado.armarCard(publicacion,"clasificado",Optional.empty());
        Assertions.assertTrue(card.unidad().isEmpty());
    }


}