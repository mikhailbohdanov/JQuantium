package com.jquantium.util.auto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 01/10/2015.
 */
public class AutoList<E> {
    private List<E> elements = new ArrayList<>();
    private List<Auto> observers = new ArrayList<>();

    private void _add(E element) {
        //TODO insert into database

        for (Auto auto : observers) {
            auto.added(element);
        }
    }

    private void _update(E element) {
        //TODO update in database

        for (Auto auto : observers) {
            auto.updated(element);
        }
    }

    private void _remove(E element) {
        //TODO remove from database

        for (Auto auto : observers) {
            auto.removed(element);
        }
    }

    public void create(E element) {
        if (elements.add(element)) {
            _add(element);
        }
    }



    public E get(int index) {
        return elements.get(index);
    }

    public List<E> get() {
        return new ArrayList<>(elements);
    }

    public List<E> get(int offset, int length) {
        int i = this.elements.size() - offset;
        length = i < length ? i : length;

        List<E> elements = new ArrayList<>(length);

        for (i = 0; i < length; i++) {
            elements.add(this.elements.get(i + offset));
        }

        return elements;
    }

    public void remove(int index) {
        E element = elements.get(index);

        if (elements.remove(index) != null) {
            _remove(element);
        }
    }

    public void remove(E element) {
        if (elements.remove(element)) {
            _remove(element);
        }
    }



    public void register(Auto<E> observer) {
        observers.add(observer);

        elements.forEach(observer::added);
    }

    public void unregister(Auto<E> observer) {
        observers.remove(observer);

        elements.forEach(observer::removed);
    }
}
