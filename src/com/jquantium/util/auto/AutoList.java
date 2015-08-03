package com.jquantium.util.auto;

import com.jquantium.bean.localization.Language;
import com.jquantium.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class AutoList<E> extends ArrayList<E> {
    private Class<E> eClass;

    private DAO dao;

    @Autowired
    public AutoList(DAO dao) {
        eClass          = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    public AutoList(Collection<? extends E> elements) {
        super(elements);


    }




}
