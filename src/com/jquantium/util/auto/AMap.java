package com.jquantium.util.auto;

import java.util.Map;

import static com.jquantium.util.Assert.NULL;

/**
 * Created by Mykhailo_Bohdanov on 11/02/2016.
 */
public abstract class AMap<K, E> implements GeneratedKey<K, E> {
    protected Map<K, E> elementMap;
    protected AList<? super E> elementList;

    public AMap(AList<? super E> elementList) {
//        list.registerMap(this);
        this.elementList = elementList;
    }

    private K findKey(E element) {
        if (NULL(element) || !elementMap.containsValue(element)) {
            return null;
        }

        K key = null;

        for (Map.Entry<K, ?> entry : elementMap.entrySet()) {
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

        return elementMap.get(key);
    }

    public boolean containsKey(K key) {
        return !NULL(key) && elementMap.containsKey(key);
    }
    public boolean containsValue(E element) {
        return element != null && elementMap.containsValue(element);
    }

    protected boolean addElementObject(Object element) {
        return addElement((E) element);
    }
    protected boolean addElement(E element) {
        if (!filter(element)) {
            return false;
        }

        return !NULL(elementMap.put(getKey(element), element));
    }
    protected boolean updateElement(E element) {
        K oldKey, newKey = getKey(element);

        if (elementMap.containsValue(element) && elementMap.get(newKey) != element) {
            oldKey = findKey(element);

            if (!NULL(oldKey) && oldKey != newKey) {
                elementMap.remove(oldKey);
                elementMap.put(newKey, element);

                return true;
            }
        }

        return false;
    }
    protected boolean removeElement(E element) {
        K key = findKey(element);

        return !NULL(elementMap.remove(key));
    }
}
