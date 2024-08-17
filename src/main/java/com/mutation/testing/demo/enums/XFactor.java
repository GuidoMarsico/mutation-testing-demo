package com.mutation.testing.demo.enums;

public enum XFactor {

        XBLUE("x-blue",0,0),
        XGOLD("x-gold",0,45),
        XGREEN("x-green",45,70),
        XRED("x-red",70,90),
        XBLACK("x-black",90,101);

    public final String xFactor;
    public final Integer min;
    public final Integer max;

    XFactor(String nombre,Integer min, Integer max) {
        this.xFactor = nombre;
        this.min= min;
        this.max = max;
    }
}
