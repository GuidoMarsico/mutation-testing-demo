package com.mutation.testing.demo.comparator;

import com.mutation.testing.demo.response.Card;

import java.util.Comparator;

public class PriceComparator  implements Comparator<Card> {
    @Override
    public int compare(Card c1, Card c2) {
        Integer precio1 = c1.precio() != null ? c1.precio() : 0;
        Integer precio2 = c2.precio() != null ? c2.precio() : 0;
        return precio2 - precio1;
    }
}
