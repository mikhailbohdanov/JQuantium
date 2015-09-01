package com.jquantium.util.event;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mykhailo_Bohdanov on 04/08/2015.
 */
public interface Dispatcher<T, E> {
    HashMap<Object, ArrayList<Dispatcher>> subscribers  = new HashMap<>();

    default void subscribe(Dispatcher subscriber) {
        if (!subscribers.containsKey(this)) {
            subscribers.put(this, new ArrayList<>());
        }

        subscribers.get(this).add(subscriber);
    }

    default void unsubscribe(Dispatcher subscriber) {
        if (subscribers.containsKey(this)) {
            subscribers.get(this).remove(subscriber);
        }
    }

    default void dispatch(T target, E before, E after) {
        if (subscribers.containsKey(this)) {
            Event<T, E> event = new Event<>(target, before, after);

            for (Dispatcher subscriber : subscribers.get(this)) {
                if (subscriber != target) {
                    subscriber.listen(event);
                }
            }
        }
    }

    void listen(Event<T, E> event);

}
