package com.mutation.testing.demo.enums;

public enum Nivel {

        GRATIS(1,"gratis",0),
        NORMAL(2,"normal",1),
        NORMAL_DESA(3,"normal-desa",3),
        EXCELENTE(4,"excelente",4),
        EXCELENTE_DESA(5,"excelente-desa",8);

    public final Integer id;
    public final String nombre;
    public final Integer peso;

     Nivel(Integer id, String nombre, Integer peso) {
        this.id= id;
        this.nombre=nombre;
        this.peso=peso;

    }
}
