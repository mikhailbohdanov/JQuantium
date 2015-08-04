package com.jquantium.util.auto;

import com.jquantium.util.event.Dispatcher;
import com.jquantium.util.event.Event;

import java.util.HashMap;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public abstract class AutoHashMap<K, E> extends HashMap<K, E> implements GenerateKey<K, E>, Dispatcher<AutoHashMap, E> {

    public AutoHashMap(AutoList<E> autoList) {

    }

    @Override
    public void listen(Event<AutoHashMap, E> event) {

    }
}
