package com.jquantium.util.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 04/08/2015.
 */
public interface Dispatcher<T, E> {
    List<Dispatcher> subscribers = new ArrayList<>();

    default void subscribe(Dispatcher subscriber) {
        subscribers.add(subscriber);
    }

    default void unsubscribe(Dispatcher subscriber) {
        subscribers.remove(subscriber);
    }

    default void dispatch(T target, E eventData) {
        Event<T, E> event = new Event<>(target, eventData);

        for (Dispatcher subscriber : subscribers) {
            if (subscriber != this) {
                subscriber.listen(event);
            }
        }
    }

    void listen(Event<T, E> event);

}
