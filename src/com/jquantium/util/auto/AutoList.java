package com.jquantium.util.auto;

import com.jquantium.dao.DAONew;
import com.jquantium.dao.queries.Select;
import com.jquantium.helper.DAOHelper;
import com.jquantium.util.event.Broadcaster;
import com.jquantium.util.event.Dispatcher;
import com.jquantium.util.event.Event;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class AutoList<E extends Broadcaster> extends ArrayList<E> implements Dispatcher<AutoList, E> {
    private Class<E> eClass;

    private DAONew dao;

    public AutoList() {
        super();

        eClass          = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Select<E> select = DAOHelper.getAll(eClass);
        this.addAll(dao.getList(select));
    }
    public AutoList(Collection<? extends E> elements) {
        super(elements);

    }

    @Override
    public void listen(Event<AutoList, E> event) {

    }


}
