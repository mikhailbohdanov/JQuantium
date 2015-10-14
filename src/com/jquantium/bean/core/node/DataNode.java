package com.jquantium.bean.core.node;

import com.jquantium.bean.Url;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Home on 14.10.2015.
 */
public class DataNode extends Node {
    private Url url;

    private DataSource source;
    private NamedParameterJdbcTemplate jdbc;

    public DataNode(String name, NodeType type, String url) {
        super(name, type, url);
    }
    public DataNode(int nodeId, String name, NodeType type, String url) {
        super(nodeId, name, type, url);
    }

    public DataSource getSource() {
        return source;
    }

    public NamedParameterJdbcTemplate getJdbc() {
        return jdbc;
    }

    public boolean init() {
        return false;
    }
}
