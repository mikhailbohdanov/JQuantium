package com.jquantium.dao;

import com.jquantium.bean.core.node.DataNode;
import com.jquantium.service.CORE;
import com.jquantium.service.Nodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 02/10/2015.
 */
@Service
public class DAO {
    private DataNode mainNode;

    private DataNode getNode(String nodeName) {
        return CORE.nodes.getDataNode(nodeName);
    }

    private int putRow(String sql, MapSqlParameterSource map, NamedParameterJdbcTemplate jdbc) throws Exception {
        if (jdbc == null) {
            throw new Exception();
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
        if (jdbc == null) {
            throw new Exception();
        }

        return jdbc.queryForObject(sql, map, eClass);
    }

    private <E> List<E> getRowList(String sql, MapSqlParameterSource map, Class<E> eClass, NamedParameterJdbcTemplate jdbc) throws Exception {
        if (jdbc == null) {
            throw new Exception();
        }

        return jdbc.queryForList(sql, map, eClass);
    }

    @Autowired
    public void init(Nodes nodes) {
        mainNode = nodes.getDataNode("main");
    }

    public int putRow(String sql, MapSqlParameterSource map) throws Exception {
        return putRow(sql, map, mainNode);
    }
    public int putRow(String sql, MapSqlParameterSource map, String nodeName) throws Exception {
        return putRow(sql, map, getNode(nodeName));
    }
    public int putRow(String sql, MapSqlParameterSource map, DataNode node) throws Exception {
        if (sql == null || node == null || !node.isInited()) {
            throw new Exception();
        }

        if (map == null) {
            map = new MapSqlParameterSource();
        }

        return putRow(sql, map, node.getJdbc());
    }

    public <E> E getRow(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return getRow(sql, map, eClass, mainNode);
    }
    public <E> E getRow(String sql, MapSqlParameterSource map, Class<E> eClass, String nodeName) throws Exception {
        return getRow(sql, map, eClass, getNode(nodeName));
    }
    public <E> E getRow(String sql, MapSqlParameterSource map, Class<E> eClass, DataNode node) throws Exception {
        if (sql == null || eClass == null || node == null || !node.isInited()) {
            throw new Exception();
        }

        if (map == null) {
            map = new MapSqlParameterSource();
        }

        return getRow(sql, map, eClass, node.getJdbc());
    }

    public <E> List<E> getRowList(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return getRowList(sql, map, eClass, mainNode);
    }
    public <E> List<E> getRowList(String sql, MapSqlParameterSource map, Class<E> eClass, String nodeName) throws Exception {
        return getRowList(sql, map, eClass, getNode(nodeName));
    }
    public <E> List<E> getRowList(String sql, MapSqlParameterSource map, Class<E> eClass, DataNode node) throws Exception {
        if (sql == null || eClass == null || node == null || !node.isInited()) {
            throw new Exception();
        }

        if (map == null) {
            map = new MapSqlParameterSource();
        }

        return getRowList(sql, map, eClass, node.getJdbc());
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
