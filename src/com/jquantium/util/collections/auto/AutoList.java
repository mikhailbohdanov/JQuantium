package com.jquantium.util.collections.auto;

import com.jquantium.util.collections.observer.Observable;
import com.jquantium.util.collections.observer.Observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class AutoList<E> extends ArrayList<E> implements Observable<Integer, E>, Observer<E> {
    private List<Observer> observerList = new ArrayList<>();

    private void init() {
        //TODO bind all elements for watch edit any object and sync with database
    }

    public AutoList() {
        super();

        init();
    }
    public AutoList(Collection<? extends E> elements) {
        super(elements);

        init();
    }

    @Override
    public void register(Observer observer) {
        observerList.add(observer);

        this.forEach(observer::added);
    }

    @Override
    public void unregister(Observer observer) {
        observerList.remove(observer);

        this.forEach(observer::removed);
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
        super.add(element);
    }

    @Override
    public void removed(E element) {
        super.remove(element);
    }

    @Override
    public boolean add(E e) {
        notifyAdd(e);

        return super.add(e);
    }


    //    @Override
//    public boolean add(E element) {
//        dispatch(this, null, element);
//
//        return super.add(element);
//    }
//
//    @Override
//    public void add(int index, E element) {
//        dispatch(this, null, element);
//
//        super.add(index, element);
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends E> c) {
//        for (E element : c) {
//            dispatch(this, null, element);
//        }
//
//        return super.addAll(c);
//    }
//
//    @Override
//    public boolean addAll(int index, Collection<? extends E> c) {
//        for (E element : c) {
//            dispatch(this, null, element);
//        }
//
//        return super.addAll(index, c);
//    }
//
//    @Override
//    public E set(int index, E element) {
//        dispatch(this, get(index), element);
//
//        return super.set(index, element);
//    }
//
//    @Override
//    public E remove(int index) {
//        E element = super.remove(index);
//        dispatch(this, element, null);
//
//        return element;
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        dispatch(this, (E) o, null);
//
//        return super.remove(o);
//    }
//
//    @Override
//    protected void removeRange(int fromIndex, int toIndex) {
//        for (int i = fromIndex; i < toIndex; i++) {
//            dispatch(this, get(i), null);
//        }
//
//        super.removeRange(fromIndex, toIndex);
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//        for (Object element : c) {
//            dispatch(this, (E) element, null);
//        }
//
//        return super.removeAll(c);
//    }
//    @Override
//    public int hashCode() {
//        return (hashCode == 0) ? hashCode = super.hashCode() : hashCode;
//    }

}
