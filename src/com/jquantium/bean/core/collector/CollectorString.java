package com.jquantium.bean.core.collector;

import com.jquantium.bean.core.collector.types.CollectorItemInteger;
import com.jquantium.bean.core.collector.types.CollectorItemString;

import java.util.TreeMap;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
public class CollectorString<T extends CollectorItemInteger & CollectorItemString> extends CollectorInteger<T> {
    private TreeMap<String, T> items        = new TreeMap<>();

    public CollectorString() {
        super();

    }

    public void addItem(T item) {
        super.addItem(item);

        items.put(item.getKeyString(), item);
    }

}
