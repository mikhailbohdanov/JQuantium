package com.jquantium.util.auto;

import com.jquantium.util.event.Broadcaster;
import com.jquantium.util.event.Dispatcher;
import com.jquantium.util.event.Event;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public abstract class AutoTreeMap<K, E extends Broadcaster> extends HashMap<K, E> implements GenerateKey<K, E>, Dispatcher<AutoTreeMap, E> {

    @Override
    public void add(Event<AutoTreeMap, E> event) {

    }

    @Override
    public void replace(Event<AutoTreeMap, E> event) {

    }

    @Override
    public void remove(Event<AutoTreeMap, E> event) {

    }
}
