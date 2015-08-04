package com.jquantium.util.auto;

import com.jquantium.dao.DAO;
import com.jquantium.util.event.Dispatcher;
import com.jquantium.util.event.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class AutoList<E> extends ArrayList<E> implements Dispatcher<AutoList, E> {
    private Class<E> eClass;

    private DAO dao;

    private void updateAll() {

    }

    public AutoList() {
        eClass          = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    public AutoList(Collection<? extends E> elements) {
        super(elements);

    }

    @Override
    public void listen(Event<AutoList, E> event) {

    }


}
