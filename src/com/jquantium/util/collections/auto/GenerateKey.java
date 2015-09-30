package com.jquantium.util.collections.auto;

import java.util.Map;

/**
 * Created by ������ on 03.08.2015.
 */
public interface GenerateKey<K, E> {

    K getKey(E element);

}
