package com.jquantium.util.memory;

import java.util.TreeMap;

/**
 * Created by Home on 14.10.2015.
 */
public abstract class MTreeMap<K, E> extends MMap<K, E> {
    {
        elements = new TreeMap<>();

        mList.forEach(super::addElementObject);
    }

    public MTreeMap(MList<? super E> list) {
        super(list);
    }
}
