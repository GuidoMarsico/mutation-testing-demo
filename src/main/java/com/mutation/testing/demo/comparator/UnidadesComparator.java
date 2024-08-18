package com.mutation.testing.demo.comparator;

import com.mutation.testing.demo.response.Card;

import java.util.Comparator;

public class UnidadesComparator implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        Integer cantUnidades1 = o1.unidad().size();
        Integer cantUnidades2 = o2.unidad().size();
        return cantUnidades2 - cantUnidades1;
    }
}
