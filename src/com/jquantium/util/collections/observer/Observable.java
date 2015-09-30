package com.jquantium.util.collections.observer;

/**
 * Created by Mykhailo_Bohdanov on 30/09/2015.
 */
public interface Observable<K, E> {

    void register(Observer<E> observer);

    void unregister(Observer<E> observer);

    void notifyAdd(E element);

    void notifyRemove(E element);

}
