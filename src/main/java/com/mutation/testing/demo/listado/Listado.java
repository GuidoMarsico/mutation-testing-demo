package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.model.Publicacion;
import com.mutation.testing.demo.response.Card;
import com.mutation.testing.demo.util.Util;

import java.util.List;
import java.util.Optional;

public interface Listado {

    List<Card> getListado(DataSource ds);

    default Card armarCard(Publicacion publicacion, String tipoPublicacion, Optional<List<Card>> unidad){
        String tipoPropiedad = Util.getTipoPropiedadById(publicacion.idTipoDePropiedad()).nombre;
        String nivel = Util.getNivelById(publicacion.idNivel()).nombre;
        String xFactor = Util.getXfactor(publicacion.xFactor()).xFactor;
        return new Card(publicacion.id(), tipoPropiedad, publicacion.anunciante(), publicacion.ubicacion(),
                publicacion.precio(), publicacion.fechaPublicacion(), nivel, xFactor,tipoPublicacion,unidad.orElse(List.of()));
    }


}
