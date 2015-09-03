package com.jquantium.util.auto;

import com.jquantium.util.event.Broadcaster;
import com.jquantium.util.event.Dispatcher;
import com.jquantium.util.event.Event;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class AutoList<E extends Broadcaster> extends ArrayList<E> implements Dispatcher<AutoList, E> {
    private int hashCode = 0;

    private Class<E> eClass;

    private void init() {
        //TODO bind all elements for watch edit any object and sync with database
    }

    public AutoList() {
        super();

//        eClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

//        Select<E> select = DAOHelper.getAll(eClass);
//        super.addAll(DAONew.getList(select));

        init();
    }
    public AutoList(Collection<? extends E> elements) {
        super(elements);

        init();
    }

    @Override
    public boolean add(E element) {
        dispatch(this, null, element);

        return super.add(element);
    }

    @Override
    public void add(int index, E element) {
        dispatch(this, null, element);

        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            dispatch(this, null, element);
        }

        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E element : c) {
            dispatch(this, null, element);
        }

        return super.addAll(index, c);
    }

    @Override
    public E set(int index, E element) {
        dispatch(this, get(index), element);

        return super.set(index, element);
    }

    @Override
    public E remove(int index) {
        E element = super.remove(index);
        dispatch(this, element, null);

        return element;
    }

    @Override
    public boolean remove(Object o) {
        dispatch(this, (E) o, null);

        return super.remove(o);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        for (int i = fromIndex; i < toIndex; i++) {
            dispatch(this, get(i), null);
        }

        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object element : c) {
            dispatch(this, (E) element, null);
        }

        return super.removeAll(c);
    }

    @Override
    public void add(Event<AutoList, E> event) {
        super.add(event.getAfter());
    }

    @Override
    public void replace(Event<AutoList, E> event) {
        super.set(indexOf(event.getBefore()), event.getAfter());
    }

    @Override
    public void remove(Event<AutoList, E> event) {
        super.remove(event.getBefore());
    }

    @Override
    public int hashCode() {
        return (hashCode == 0) ? hashCode = super.hashCode() : hashCode;
    }

}
