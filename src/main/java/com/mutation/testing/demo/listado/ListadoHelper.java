package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.model.Publicacion;

import java.util.List;

public class ListadoHelper {


    public static List<Publicacion> getEmprendimientos(List<Publicacion> publicacionesRaw){

        return publicacionesRaw.stream().filter(publicacion -> publicacion.idTipoDePropiedad().equals(10)).toList();
    }

    public static List<Publicacion> getUnidades(List<Publicacion> publicacionesRaw){

        return publicacionesRaw.stream().filter(publicacion -> {
            return List.of(1,2).contains(publicacion.idTipoDePropiedad()) & publicacion.idPublicacionPadre()!= null;
        }).toList();
    }


}
