package com.jquantium.dao;

import com.jquantium.dao.queries.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
    private HashMap<Integer, NamedParameterJdbcTemplate> dataMap    = new HashMap<>();

    @Autowired
    void init(@Qualifier("mainFrame") DataSource mainFrame, @Qualifier("userFrame") DataSource userFrame) {
        addData(0, mainFrame);
        addData(1, userFrame);
    }
    public void addData(int id, DataSource source) {
        dataMap.put(id, new NamedParameterJdbcTemplate(source));
    }




    public <E> List<E> getList(Select<E> select) {

        return null;
    }
    public <E> E get(Select<E> select) {

        return null;
    }




    public void exec(String sql, MapSqlParameterSource map) throws Exception {
        exec(sql, map, 0);
    }
    public void exec(String sql, MapSqlParameterSource map, int nodeId) throws Exception {
        if (sql == null || !dataMap.containsKey(nodeId)) {//TODO must throwable DataSourceNullException
            return;
        }

        if (map == null) {
            map = new MapSqlParameterSource();
        }

        try {
            dataMap
                    .get(nodeId)
                    .update(
                            sql,
                            map
                    );
        } catch (Exception e) {
            throw e;
        }
    }

    public <E> E exec(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return exec(sql, map, eClass, 0);
    }
    public <E> E exec(String sql, MapSqlParameterSource map, Class<E> eClass, int nodeId) {
        if (!dataMap.containsKey(nodeId) || sql == null) {
            return null;
        }

        if (map == null) {
            map = new MapSqlParameterSource();
        }

        try {
            return dataMap
                    .get(nodeId)
                    .queryForObject(
                            sql,
                            map,
                            eClass
                    );
        } catch (Exception e) {
            throw e;
        }



    }



}
