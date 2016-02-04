package com.jquantium.util.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by Home on 14.10.2015.
 */
public abstract class MList<E> {
    protected List<E> elements;
    private List<MMap> maps = new ArrayList<>();

    private void _add(E element) {
        for (MMap map : maps) {
            map.addElement(element);
        }
    }
    private void _update(E element) {
        for (MMap map : maps) {
            map.updateElement(element);
        }
    }
    private void _remove(E element) {
        for (MMap map : maps) {
            map.removeElement(element);
        }
    }

    public MList() {
        elements = init();

        if (elements == null) {
            elements = new ArrayList<>();
        }
    }
    public MList(List<E> elements) {
        this.elements   = elements;
    }

    public boolean create(E element) {
        if (element != null && createElement(element)) {
            return add(element);
        }

        return false;
    }

    public boolean add(E element) {
        if (element == null) {
            return false;
        }

        elements.add(element);
//        bindElement(element);
        _add(element);

        return true;
    }
    public void addAll(List<E> elements) {
        if (elements != null) {
            elements.forEach(this::add);
        }
    }

    public E get(int index) {
        return elements.get(index);
    }
    public void forEach(Consumer<? super E> action) {
        if (elements != null) {
            Objects.requireNonNull(action);
            elements.forEach(action::accept);
        }
    }

    public E remove(int index) {
        return remove(elements.get(index));
    }
    public E remove(E element) {
        if (element != null && removeElement(element)) {
            elements.remove(element);
//            unbindElement(element);
            _remove(element);

            return element;
        }

        return null;
    }

    protected void registerMap(MMap map) {
        if (map != null) {
            maps.add(map);
        }
    }
    protected void unregisterMap(MMap map) {
        if (map != null) {
            maps.remove(map);
        }
    }

    protected abstract List<E> init();

    protected abstract boolean createElement(E element);
    protected abstract boolean updateElement(E element);
    protected abstract boolean removeElement(E element);
}
