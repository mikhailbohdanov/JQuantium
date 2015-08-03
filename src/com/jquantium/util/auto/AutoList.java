package com.jquantium.util.auto;

import com.jquantium.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class AutoList<E> extends ArrayList<E> implements Dispatcher<E> {
    private List<Dispatcher<E>> subscribers     = new ArrayList<>();

    private Class<E> eClass;

    private DAO dao;

    @Autowired
    public AutoList(DAO dao) {
        eClass          = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    public AutoList(Collection<? extends E> elements) {
        super(elements);


    }

    @Override
    public void subscribe(Dispatcher subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void listen(E element) {
        this.add(element);
    }

    @Override
    public void dispatch(E element) {

    }
}
