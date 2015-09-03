package com.jquantium.util.auto;

import com.jquantium.util.event.Broadcaster;
import com.jquantium.util.event.Dispatcher;
import com.jquantium.util.event.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public abstract class AutoHashMap<K, E extends Broadcaster> extends HashMap<K, E> implements GenerateKey<K, E>, Dispatcher<AutoHashMap, E> {
    private int hashCode = 0;

    public AutoHashMap(AutoList<E> autoList) {
        autoList.subscribe(this);
        this.subscribe(autoList);



    }

    @Override
    public E put(K key, E value) {
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends E> m) {
        super.putAll(m);
    }

    @Override
    public E remove(Object key) {
        return super.remove(key);
    }

    @Override
    public E putIfAbsent(K key, E value) {
        return super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return super.remove(key, value);
    }

    @Override
    public boolean replace(K key, E oldValue, E newValue) {
        return super.replace(key, oldValue, newValue);
    }

    @Override
    public E replace(K key, E value) {
        return super.replace(key, value);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super E, ? extends E> function) {
        super.replaceAll(function);
    }

    @Override
    public void add(Event<AutoHashMap, E> event) {
        E element = event.getAfter();

        super.put(getKey(element), element);
    }

    @Override
    public void replace(Event<AutoHashMap, E> event) {

    }

    @Override
    public void remove(Event<AutoHashMap, E> event) {
        E element = event.getBefore();

        super.remove(getKey(element));
    }

    @Override
    public int hashCode() {
        return (hashCode == 0) ? hashCode = super.hashCode() : hashCode;
    }
}
