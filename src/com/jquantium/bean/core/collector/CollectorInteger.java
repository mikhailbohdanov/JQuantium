package com.jquantium.bean.core.collector;

import com.jquantium.bean.core.collector.types.CollectorItemInteger;

import java.util.TreeMap;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
public class CollectorInteger<T extends CollectorItemInteger> extends Collector<T> {
    private TreeMap<Integer, T> items       = new TreeMap<>();

    public CollectorInteger() {
        super();

    }

    public void addItem(T item) {
        super.addItem(item);

        items.put(item.getKeyInteger(), item);
    }

}
