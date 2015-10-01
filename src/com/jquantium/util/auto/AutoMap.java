package com.jquantium.util.auto;

import java.util.Map;

/**
 * Created by Mykhailo_Bohdanov on 01/10/2015.
 */
public abstract class AutoMap<K, E> implements GeneratedKey<K, E>, Auto<E> {
    protected Map<K, E> elements;

    protected AutoMap(AutoList<E> autoList) {
        autoList.register(this);
    }

    private K findKey(E element) {
        K key = null;

        for (Map.Entry<K, E> entry : elements.entrySet()) {
            if (entry.getValue() == element) {
                key = entry.getKey();
                break;
            }
        }

        return key;
    }

    public E get(K key) {
        return elements.get(key);
    }

    @Override
    public boolean added(E element) {
        return elements.put(getKey(element), element) != null;
    }

    @Override
    public boolean updated(E element) {
        K oldKey = null, newKey = getKey(element);

        if (elements.containsValue(element) && elements.get(newKey) != element) {
            oldKey = findKey(element);

            if (oldKey != null && oldKey != newKey) {
                elements.remove(oldKey);
                elements.put(newKey, element);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removed(E element) {
        K key = findKey(element);

        return elements.remove(key) != null;
    }
}
