package com.jquantium.util.auto;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Mykhailo_Bohdanov on 01/10/2015.
 */
public abstract class AutoTreeMap<K, E> implements Auto<E>, GeneratedKey<K, E> {
    private Map<K, E> elements = new TreeMap<>();

    protected AutoTreeMap(AutoList<E> autoList) {
        autoList.register(this);


    }

    @Override
    public boolean added(E element) {
        return false;
    }

    @Override
    public boolean updated(E element) {
        return false;
    }

    @Override
    public boolean removed(E element) {
        return false;
    }
}
