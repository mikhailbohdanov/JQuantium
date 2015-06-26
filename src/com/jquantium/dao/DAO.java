package com.jquantium.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Repository
public class DAO {
    private HashMap<Integer, NamedParameterJdbcTemplate> dataSources   = new HashMap<Integer, NamedParameterJdbcTemplate>();

    @Autowired
    void init(@Qualifier("mainFrame") DataSource mainFrame, @Qualifier("userFrame") DataSource userFrame) {
        addNode(0, mainFrame);
        addNode(1, userFrame);
    }
    public void addNode(int nodeId, DataSource node) {
        dataSources.put(nodeId, new NamedParameterJdbcTemplate(node));
    }
    public NamedParameterJdbcTemplate getNode(final int nodeId) throws Exception {
        if (dataSources.containsKey(nodeId))
            return dataSources.get(nodeId);

        throw new Exception();//TODO make specific exception for this event
    }

    public int insertRow(String sql) throws Exception {
        return insertRow(sql, null, 0);
    }
    public int insertRow(String sql, MapSqlParameterSource map) throws Exception {
        return insertRow(sql, map, 0);
    }
    public int insertRow(String sql, MapSqlParameterSource map, final int nodeId) throws Exception {
//        if (sql == null)
//            throw new QueryException();

        KeyHolder key   = new GeneratedKeyHolder();

        try {
            getNode(nodeId)
                    .update(
                            sql,
                            map,
                            key
                    );
        } catch (Exception e) {
            throw e;
        }

        return key.getKey().intValue();
    }

    //TODO refactoring next methods
    public <T> T getRow(String sql, MapSqlParameterSource map, RowMapper<T> mapper) throws Exception {
        return getRow(sql, map, mapper, 0);
    }
    public <T> T getRow(String sql, MapSqlParameterSource map, RowMapper<T> mapper, final int nodeId) throws Exception {
        if (sql == null || !dataSources.containsKey(nodeId))
            return null;//TODO must throwable DataSourceNullException

        try {
            return dataSources
                    .get(nodeId)
                    .queryForObject(
                            sql,
                            map,
                            mapper
                    );
        } catch (Exception e) {
            throw e;
        }
    }

    public <T> List<T> getRowList(String sql, MapSqlParameterSource map, RowMapper<T> mapper) throws Exception {
        return getRowList(sql, map, mapper, 0);
    }
    public <T> List<T> getRowList(String sql, MapSqlParameterSource map, RowMapper<T> mapper, final int nodeId) throws Exception {
        if (sql == null || !dataSources.containsKey(nodeId))
            return null;//TODO must throwable DataSourceNullException

        try {
            if (map != null)
                return dataSources
                        .get(nodeId)
                        .query(
                                sql,
                                map,
                                mapper
                        );
            else
                return dataSources
                        .get(nodeId)
                        .query(
                                sql,
                                mapper
                        );
        } catch (Exception e) {
            throw e;
        }
    }

    public void exec(String sql, MapSqlParameterSource map) throws Exception {
        exec(sql, map, 0);
    }
    public void exec(String sql, MapSqlParameterSource map, int nodeId) throws Exception {
        if (sql == null || !dataSources.containsKey(nodeId))//TODO must throwable DataSourceNullException
            return;

        if (map == null)
            map     = new MapSqlParameterSource();

        try {
            dataSources
                    .get(nodeId)
                    .update(
                            sql,
                            map
                    );
        } catch (Exception e) {
            throw e;
        }
    }

    public <T> T exec(String sql, MapSqlParameterSource map, Class<T> _class) throws Exception {
        return exec(sql, map, _class, 0);
    }
    public <T> T exec(String sql, MapSqlParameterSource map, Class<T> _class, int nodeId) throws Exception {
        if (!dataSources.containsKey(nodeId) || sql == null)
            return null;

        if (map == null)
            map     = new MapSqlParameterSource();

        try {
            return dataSources
                    .get(nodeId)
                    .queryForObject(
                            sql,
                            map,
                            _class
                    );
        } catch (Exception e) {
            throw e;
        }
    }

}
