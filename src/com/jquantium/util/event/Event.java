package com.jquantium.util.event;

/**
 * Created by Mykhailo_Bohdanov on 04/08/2015.
 */
public class Event<T, E> {
    private T target;
    private E before;
    private E after;

    public Event(T target, E before, E after) {
        this.target     = target;
        this.before     = before;
        this.after      = after;
    }

    public T getTarget() {
        return target;
    }

    public E getAfter() {
        return after;
    }

    public E getBefore() {
        return before;
    }
}
