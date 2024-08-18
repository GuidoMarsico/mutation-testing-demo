package com.mutation.testing.demo.response;

import java.util.List;

public record Card(Integer id, String tipoDePropiedad, String anunciante, String ubicacion, Integer precio, String fechaPublicacion, String nivel, String xFactor, String tipoPublicacion , List<Card> unidad) {
}
