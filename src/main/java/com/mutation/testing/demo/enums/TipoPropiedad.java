package com.mutation.testing.demo.enums;

public enum TipoPropiedad {
    CASA(1,"casa"),
    DEPARTAMENTO(2,"departamento"),
    DESARROLLO(10,"desarrollo")
    ;

    public final Integer id;
    public final String nombre;

    TipoPropiedad(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
