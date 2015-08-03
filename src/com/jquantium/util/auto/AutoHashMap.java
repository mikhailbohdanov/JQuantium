package com.jquantium.util.auto;

import java.util.HashMap;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public abstract class AutoHashMap<K, E> extends HashMap<K, E> implements GenerateKey<K, E>, Dispatcher<E> {

    private AutoHashMap() {
        //TODO bind all events and fill collection
    }

    public AutoHashMap(AutoList<E> list) {

    }
}
