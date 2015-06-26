package com.jquantium.bean.core.collector;

import com.jquantium.bean.core.collector.types.CollectorItem;
import com.jquantium.bean.core.collector.types.CollectorItemInteger;

import java.util.ArrayList;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
public class Collector<T extends CollectorItem> {
    private ArrayList<T> items              = new ArrayList<>();

    public Collector() {

    }

    public void addItem(T item) {
        items.add(item);
    }


}
