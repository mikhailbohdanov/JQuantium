package com.jquantium.dao;

import com.jquantium.bean.core.node.DataNode;
import com.jquantium.helper.StringHelper;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 02/10/2015.
 */
@Service
public class DAO {
    public static final MapSqlParameterSource EMPTY_MAP_SQL_PARAMETER_SOURCE = new MapSqlParameterSource();

    private DataNode masterNode;
    private NamedParameterJdbcTemplate masterJdbc;

    public void setMasterNode(DataNode node) {
        this.masterNode = node;
        this.masterJdbc = node.getJdbc();
    }

    public int insertRow(String sql, MapSqlParameterSource map) throws Exception {
        return insertRow(sql, map, masterJdbc);
    }
    public int insertRow(String sql, MapSqlParameterSource map, NamedParameterJdbcTemplate jdbc) throws Exception {
        if (sql == null || sql.isEmpty()) {
            throw new Exception();
        }

        if (sql == null || sql.isEmpty() || map == null) {
            throw new Exception();
        }

        if (map == null) {
            map = EMPTY_MAP_SQL_PARAMETER_SOURCE;
        }

        if (jdbc == null) {
            jdbc = masterJdbc;
        }

        KeyHolder key = new GeneratedKeyHolder();

        try {
            jdbc.update(sql, map, key);
        } catch (Exception e) {
            throw e;
        }

        return key.getKey().intValue();
    }

    public <E> E selectRow(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return null;
    }
    public <E> E selectRow(String sql, MapSqlParameterSource map, Class<E> eClass, NamedParameterJdbcTemplate jdbc) throws Exception {
        return null;
    }

    public <E> List<E> selectRows(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return null;
    }
    public <E> List<E> selectRows(String sql, MapSqlParameterSource map, Class<E> eClass, NamedParameterJdbcTemplate jdbc) throws Exception {
        return null;
    }

    public <E> void updateRow(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return;
    }
    public <E> void updateRow(String sql, MapSqlParameterSource map, Class<E> eClass, NamedParameterJdbcTemplate jdbc) throws Exception {
        return;
    }

    public void exec(String sql, MapSqlParameterSource map) throws Exception {
        return;
    }
    public void exec(String sql, MapSqlParameterSource map, NamedParameterJdbcTemplate jdbc) throws Exception {
        return;
    }

    public <E> E exec(String sql, MapSqlParameterSource map, Class<E> eClass) throws Exception {
        return null;
    }
    public <E> E exec(String sql, MapSqlParameterSource map, Class<E> eClass, NamedParameterJdbcTemplate jdbc) throws Exception {
        return null;
    }

}
