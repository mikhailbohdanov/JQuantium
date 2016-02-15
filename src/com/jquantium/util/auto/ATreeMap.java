package com.jquantium.util.auto;

import java.util.TreeMap;

/**
 * Created by Mykhailo_Bohdanov on 15/02/2016.
 */
public abstract class ATreeMap<K, E> extends AMap<K, E> {
    {
        elementMap = new TreeMap<>();

        elementList.forEach(super::addElementObject);
    }

    public ATreeMap(AList<? super E> list) {
        super(list);
    }
}
