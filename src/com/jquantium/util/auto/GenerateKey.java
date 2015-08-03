package com.jquantium.util.auto;

/**
 * Created by Михаил on 03.08.2015.
 */
public interface GenerateKey<K, E> {

    K getKey(E element);

}
