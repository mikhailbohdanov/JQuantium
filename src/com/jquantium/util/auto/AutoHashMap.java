package com.jquantium.util.auto;

import com.jquantium.util.event.Broadcaster;
import com.jquantium.util.event.Dispatcher;
import com.jquantium.util.event.Event;

import java.util.HashMap;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public abstract class AutoHashMap<K, E extends Broadcaster> extends HashMap<K, E> implements GenerateKey<K, E>, Dispatcher<AutoHashMap, E> {

    public AutoHashMap(AutoList<E> autoList) {
        autoList.subscribe(this);
        this.subscribe(autoList);



    }

    @Override
    public void listen(Event<AutoHashMap, E> event) {
        E before    = event.getBefore();
        E after     = event.getAfter();

        if (before != null && after != null) {
            remove(getKey(before));
            put(getKey(after), after);
        } else if (before != null && after == null) {
            remove(getKey(before));
        } else {
            put(getKey(after), after);
        }
    }

}
