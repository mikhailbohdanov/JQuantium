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
    private Class<E> eClass;

    private void init() {
        //TODO bind all elements for watch edit any object and sync with database
    }

    private void addElement(E element) {
        dispatch(this, null, element);
    }
    private void updateElement(E before, E after) {
        dispatch(this, before, after);
    }
    private void removeElement(E element) {
        dispatch(this, null, element);
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
        addElement(element);
        return super.add(element);
    }

    @Override
    public void add(int index, E element) {
        addElement(element);
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            addElement(element);
        }

        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E element : c) {
            addElement(element);
        }

        return super.addAll(index, c);
    }

    @Override
    public E set(int index, E element) {
        updateElement(get(index), element);

        return super.set(index, element);
    }

    @Override
    public E remove(int index) {
        removeElement(get(index));

        return super.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        removeElement((E) o);

        return super.remove(o);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        for (int i = fromIndex; i <= toIndex; i++) {
            removeElement(get(i));
        }

        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object element : c) {
            removeElement((E) element);
        }

        return super.removeAll(c);
    }

    @Override
    public void listen(Event<AutoList, E> event) {
        E before    = event.getBefore();
        E after     = event.getAfter();

        if (before != null && after != null) {
            set(this.indexOf(before), after);
        } else if (before != null && after == null) {
            remove(before);
        } else {
            add(after);
        }
    }
}
