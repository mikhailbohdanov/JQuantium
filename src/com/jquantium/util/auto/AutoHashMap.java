package com.jquantium.util.auto;

import java.util.HashMap;

/**
 * Created by Mykhailo_Bohdanov on 01/10/2015.
 */
public abstract class AutoHashMap<K, E> extends AutoMap<K, E> implements Auto<E>, GeneratedKey<K, E> {
    {
        elements = new HashMap<>();
    }

    public AutoHashMap(AutoList<E> autoList) {
        super(autoList);
    }
}
