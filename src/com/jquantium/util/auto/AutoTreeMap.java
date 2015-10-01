package com.jquantium.util.auto;

import java.util.TreeMap;

/**
 * Created by Mykhailo_Bohdanov on 01/10/2015.
 */
public abstract class AutoTreeMap<K, E> extends AutoMap<K, E> implements Auto<E>, GeneratedKey<K, E> {
    {
        elements = new TreeMap<>();
    }

    public AutoTreeMap(AutoList<E> autoList) {
        super(autoList);

    }
}
