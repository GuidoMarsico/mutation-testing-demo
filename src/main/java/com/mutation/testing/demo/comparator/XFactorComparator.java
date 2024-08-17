package com.mutation.testing.demo.comparator;

import com.mutation.testing.demo.enums.XFactor;
import com.mutation.testing.demo.response.Card;
import com.mutation.testing.demo.util.Util;

import java.util.Comparator;

public class XFactorComparator implements Comparator<Card> {

    @Override
    public int compare(Card c1, Card c2) {
        XFactor xf1 = Util.getXfactorByNombre(c1.xFactor());
        XFactor xf2 = Util.getXfactorByNombre(c2.xFactor());
        return xf2.max - xf1.max;
    }
}
