package com.jquantium.util.memory;

import com.jquantium.util.observer.Observable;

import java.util.HashMap;

/**
 * Created by Home on 14.10.2015.
 */
public abstract class MHashMap<K, E> extends MMap<K, E> {
    {
        elements = new HashMap<>();

        mList.forEach(super::addElementObject);
    }

    public MHashMap(MList<? super E> list) {
        super(list);
    }
}
