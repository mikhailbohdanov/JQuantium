package com.jquantium.util.memory;

import java.util.Map;

/**
 * Created by Home on 14.10.2015.
 */
public abstract class MMap<K, E> implements GeneratedKey<K, E> {
    protected Map<K, E> elements;
    protected MList<? super E> mList;

    public MMap(MList<? super E> list) {
        list.registerMap(this);
        mList = list;
    }

    private K findKey(E element) {
        if (element == null || !elements.containsValue(element)) {
            return null;
        }

        K key = null;

        for (Map.Entry<K, ?> entry : elements.entrySet()) {
            if (entry.getValue() == element) {
                key = entry.getKey();
                break;
            }
        }

        return key;
    }

    protected boolean filter(E element) {
        return true;
    }

    public E get(K key) {
        if (!containsKey(key)) {
            return null;
        }

        return elements.get(key);
    }

    public boolean containsKey(K key) {
        return key != null && elements.containsKey(key);
    }
    public boolean containsValue(E element) {
        return element != null && elements.containsValue(element);
    }

    protected boolean addElementObject(Object element) {
        return addElement((E) element);
    }
    protected boolean addElement(E element) {
        if (!filter(element)) {
            return false;
        }

        return elements.put(getKey(element), element) != null;
    }
    protected boolean updateElement(E element) {
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
    protected boolean removeElement(E element) {
        K key = findKey(element);

        return elements.remove(key) != null;
    }

}
