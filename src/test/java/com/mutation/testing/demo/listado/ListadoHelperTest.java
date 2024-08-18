package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.model.Publicacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListadoHelperTest {

    List<Publicacion> publicacionList;

    @BeforeEach
    void setUp() {
        publicacionList = new ArrayList<>();
        publicacionList.add(new Publicacion(1,null,1,"Pepe Argento","Flores Caba",200,1,0,"10-02-2010"));
        publicacionList.add(new Publicacion(2,null,10,"Ricky Ricon","Puerto Madero",null,5,100,"01-01-2024"));
        publicacionList.add(new Publicacion(3,2,2,"Ricky Ricon","Puerto Madero",100000,5,95,"01-01-2024"));
        publicacionList.add(new Publicacion(4,2,2,"Ricky Ricon","Puerto Madero",50000,5,97,"01-01-2024"));
        publicacionList.add(new Publicacion(5,null,10,"Mister Burns","Springfield Soho",null,3,70,"20-06-2024"));
        publicacionList.add(new Publicacion(6,5,2,"Mister Burns","Springfield Soho",300000,3,50,"20-06-2024"));
        publicacionList.add(new Publicacion(7,5,2,"Mister Burns","Springfield Soho",500000,3,20,"20-06-2024"));
        publicacionList.add(new Publicacion(8,null,2,"Juncito","Palermo Hollywood",30000,4,35,"22-09-2023"));
    }

    @Test
    void getEmprendimientosSon2() {
        Assertions.assertEquals(2,ListadoHelper.getEmprendimientos(publicacionList).size());
    }

    @Test
    void getUnidades() {
    }
}