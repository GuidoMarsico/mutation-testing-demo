package com.mutation.testing.demo.listado;

import com.mutation.testing.demo.datasource.DataSource;
import com.mutation.testing.demo.response.Card;

import java.util.List;

public interface Listado {

    List<Card> getListado(DataSource ds);

    default List<Card> sortListado(List<Card> listadoToSort){

        return listadoToSort;
    }


}
