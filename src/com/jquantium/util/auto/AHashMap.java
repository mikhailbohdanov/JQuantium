package com.jquantium.util.auto;

import java.util.HashMap;

/**
 * Created by Mykhailo_Bohdanov on 15/02/2016.
 */
public abstract class AHashMap<K, E> extends AMap<K, E> {
    {
        elementMap = new HashMap<>();

        elementList.forEach(super::addElementObject);
    }

    public AHashMap(AList<? super E> list) {
        super(list);
    }
}
