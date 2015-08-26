package com.jquantium.dao;

import com.jquantium.dao.queries.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 26/08/2015.
 */
@Repository
public class DAONew {
    private NamedParameterJdbcTemplate data;

    @Autowired
    void init(DataSource mainFrame) {
        data = new NamedParameterJdbcTemplate(mainFrame);
    }

    public <E> List<E> getList(Select<E> select) {

        return null;
    }
    public <E> E get(Select<E> select) {

        return null;
    }





}
