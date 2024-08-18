package com.mutation.testing.demo.controllers;

import com.mutation.testing.demo.enums.OrderBy;
import com.mutation.testing.demo.enums.TipoPublicacion;
import com.mutation.testing.demo.response.Card;
import com.mutation.testing.demo.response.ListadoResponse;
import com.mutation.testing.demo.service.ServiceListado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class Api {

    private final ServiceListado serviceListado;

    @Autowired
    public Api(ServiceListado serviceListado) {
        this.serviceListado = serviceListado;
    }

    @GetMapping("/listado")
    @ResponseBody
    public ListadoResponse getListado(@RequestParam(required = false) List<Integer> publicacionesExclude,
                                      @RequestParam(required = false) List<String> anunciantesExclude,
                                      @RequestParam(defaultValue = "DEFAULT") OrderBy order,
                                      @RequestParam(defaultValue = "CLASIFICADO") TipoPublicacion tipoPublicacion){

        Optional<List<Integer>> pubExclude = Optional.ofNullable(publicacionesExclude);
        Optional<List<String>> aunExclude = Optional.ofNullable(anunciantesExclude);
        SearchParams searchParams = new SearchParams(pubExclude,aunExclude,order,tipoPublicacion);
        List<Card> cards = this.serviceListado.armarListado(searchParams);

        return new ListadoResponse(cards.size(),cards, order);
    }


}
