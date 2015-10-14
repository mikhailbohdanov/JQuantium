package com.jquantium.util.observer;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Home on 13.10.2015.
 */
public interface Observable<E> {

    void register(Consumer<? super E> action);

    void unregister(Consumer<? super E> action);

    void fireEvent();
}
