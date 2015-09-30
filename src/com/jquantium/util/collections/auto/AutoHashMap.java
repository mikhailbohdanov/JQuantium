package com.jquantium.util.collections.auto;

import com.jquantium.util.collections.observer.Observable;
import com.jquantium.util.collections.observer.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public abstract class AutoHashMap<K, E> extends HashMap<K, E> implements GenerateKey<K, E>, Observable<K, E>, Observer<E> {
    private List<Observer> observerList = new ArrayList<>();

    public AutoHashMap(AutoList<? extends E> autoList) {
        autoList.register(this);
        this.register(autoList);

    }

    // - Auto generate key
    public E put(E value) {
        return put(getKey(value), value);
    }

    @Override
    public E put(K key, E value) {
        E out = super.put(key, value);

        if (out != null) {
            notifyAdd(value);
        }

        return out;
    }

    @Override
    public void putAll(Map<? extends K, ? extends E> m) {
        super.putAll(m);

//        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
//            K key = e.getKey();
//            V value = e.getValue();
//            putVal(hash(key), key, value, false, evict);
//        }
    }

    @Override
    public E remove(Object key) {
        return super.remove(key);
    }

    @Override
    public void clear() {
        super.clear();
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

    // - Observable methods

    @Override
    public void register(Observer observer) {
        observerList.add(observer);

        for (Map.Entry<K, E> entry : this.entrySet()) {
            observer.added(entry.getValue());
        }
    }

    @Override
    public void unregister(Observer observer) {
        observerList.remove(observer);

        for (Map.Entry<K, E> entry : this.entrySet()) {
            observer.removed(entry.getValue());
        }
    }

    @Override
    public void notifyAdd(E element) {
        for (Observer observer : observerList) {
            observer.added(element);
        }
    }

    @Override
    public void notifyRemove(E element) {
        for (Observer observer : observerList) {
            observer.removed(element);
        }
    }

    @Override
    public void added(E element) {
        super.put(getKey(element), element);
    }

    @Override
    public void removed(E element) {
        this.remove(element);
    }

}
