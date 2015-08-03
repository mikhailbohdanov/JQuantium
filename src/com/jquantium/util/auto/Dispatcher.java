package com.jquantium.util.auto;

import java.util.Map;

/**
 * Created by Михаил on 03.08.2015.
 */
public interface Dispatcher<E> {

    void subscribe(Dispatcher<E> subscriber);

    void listen(E element);

    void dispatch(E element);

}
