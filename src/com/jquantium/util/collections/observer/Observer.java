package com.jquantium.util.collections.observer;

/**
 * Created by Mykhailo_Bohdanov on 30/09/2015.
 */
public interface Observer<E> {

    void added(E element);

    void removed(E element);

}
