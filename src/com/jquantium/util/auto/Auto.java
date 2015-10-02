package com.jquantium.util.auto;

/**
 * Created by Mykhailo_Bohdanov on 01/10/2015.
 */
public interface Auto<E> {

    boolean added(E element);

    boolean updated(E element);

    boolean removed(E element);

}
