package com.jquantium.util.auto;

import com.jquantium.bean.localization.Language;
import com.jquantium.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.Collection;
>>>>>>> acf2d1c9ee0b2a6c3291f7481fa7ca0c990bc298

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

<<<<<<< HEAD
    }
=======


>>>>>>> acf2d1c9ee0b2a6c3291f7481fa7ca0c990bc298
}
