package com.jquantium.dao;

import com.jquantium.bean.core.DataNode;
import com.jquantium.util._memory.MemoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 02/10/2015.
 */
@Service
public class DAO {
    private MemoryList<DataNode> dataNodes      = new MemoryList<DataNode>() {
        @Override
        protected List<DataNode> init() {
            return null;
        }

        @Override
        protected boolean createElement(DataNode element) {
            return false;
        }

        @Override
        protected boolean updateElement(DataNode element) {
            return false;
        }

        @Override
        protected boolean removeElement(DataNode element) {
            return false;
        }
    };

    private static HashMap<Integer, NamedParameterJdbcTemplate> dataMap = new HashMap<>();

    private NamedParameterJdbcTemplate getJdbc() {
        return getJdbc(0);
    }
    private NamedParameterJdbcTemplate getJdbc(int id) {
        return dataMap.get(id);
    }

    @Autowired
    void init(@Qualifier("main") DataSource mainFrame) {


    }

    public void addSource(int id, DataSource source) {
        dataMap.put(id, new NamedParameterJdbcTemplate(source));
    }

    private int putRow(String sql, MapSqlParameterSource map, NamedParameterJdbcTemplate jdbc) throws Exception {
        if (sql == null || sql.isEmpty()) {
            throw new Exception();
        }

        if (map == null) {
            map = new MapSqlParameterSource();
        }

        if (jdbc == null) {
            jdbc = getJdbc();
        }

        KeyHolder key = new GeneratedKeyHolder();

        try {
            jdbc.update(sql, map, key);
        } catch (Exception e) {
            throw e;
        }

        return key.getKey().intValue();
    }

    private <E> E getRow(String sql, MapSqlParameterSource map, Class<E> eClass, NamedParameterJdbcTemplate jdbc) throws Exception {
        if (sql == null || sql.isEmpty() || eClass == null) {
            throw new Exception();
        }

        if (map == null) {
            map = new MapSqlParameterSource();
        }

        if (jdbc == null) {
            jdbc = getJdbc();
        }

        return jdbc.queryForObject(sql, map, eClass);
    }

    private <E> List<E> getRowList(String sql, MapSqlParameterSource map, Class<E> eClass, NamedParameterJdbcTemplate jdbc) throws Exception {
        return null;
    }


    public int putRow(String sql, MapSqlParameterSource map) throws Exception {
        return putRow(sql, map, 0);
    }
    public int putRow(String sql, MapSqlParameterSource map, int nodeId) throws Exception {
        NamedParameterJdbcTemplate jdbc = getJdbc();
        KeyHolder key = new GeneratedKeyHolder();

        try {
            jdbc
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

    public <E> E getRow(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return getRow(sql, map, eClass, 0);
    }
    public <E> E getRow(String sql, MapSqlParameterSource map, Class<E> eClass, int nodeId) throws Exception {
        return null;
    }

    public <E> List<E> getRowList(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return getRowList(sql, map, eClass, 0);
    }
    public <E> List<E> getRowList(String sql, MapSqlParameterSource map, Class<E> eClass, int nodeId) throws Exception {
        return null;
    }

    public void exec(String sql, MapSqlParameterSource map) throws Exception {
        exec(sql, map, 0);
    }
    public void exec(String sql, MapSqlParameterSource map, int nodeId) throws Exception {
        return;
    }

    public <E> E exec(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return exec(sql, map, eClass, 0);
    }
    public <E> E exec(String sql, MapSqlParameterSource map, Class<E> eClass, int nodeId) throws Exception {
        return null;
    }







}
