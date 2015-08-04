package com.jquantium.util.event;

/**
 * Created by Mykhailo_Bohdanov on 04/08/2015.
 */
public class Event<T, E> {
    private T target;
    private E eventData;

    public Event(T target, E eventData) {
        this.target     = target;
        this.eventData  = eventData;
    }

    public T getTarget() {
        return target;
    }

    public E getEventData() {
        return eventData;
    }

}
