package com.mutation.testing.demo.comparator;

import com.mutation.testing.demo.response.Card;
import com.mutation.testing.demo.util.Util;

import java.util.Comparator;

public class NivelComparator  implements Comparator<Card> {


    @Override
    public int compare(Card c1, Card c2) {
        Integer pesoNivel1 = Util.getNivelByNombre(c1.nivel()).peso;
        Integer pesoNivel2 = Util.getNivelByNombre(c2.nivel()).peso;

        return pesoNivel2 - pesoNivel1;
    }
}
