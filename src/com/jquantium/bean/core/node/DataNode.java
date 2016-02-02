package com.jquantium.bean.core.node;

import com.jquantium.bean.Url;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Home on 14.10.2015.
 */
public class DataNode extends Node {
    private Url url;

    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbc;

    public DataNode() {
        super();
    }
    public DataNode(String name, NodeType type, String url) {
        super(name, type, url);
    }
    public DataNode(int nodeId, String name, NodeType type, String url) {
        super(nodeId, name, type, url);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public NamedParameterJdbcTemplate getJdbc() {
        return jdbc;
    }

    public boolean isInited() {
        return jdbc != null;
    }

    public boolean init() {
        if (isInited()) {
            return true;
        }

        MysqlDataSource dataSource = new MysqlDataSource();

        this.url = new Url(super.url);

        dataSource.setUrl(super.url);
        dataSource.setUser(url.getUserName());
        dataSource.setPassword(url.getPassword());

        return init(dataSource);
    }
    public boolean init(DataSource dataSource) {
        if (isInited()) {
            return true;
        }

        this.dataSource = dataSource;

        try {
            jdbc = new NamedParameterJdbcTemplate(dataSource);
        } catch (Exception e) {//TODO exception
            return false;
        }

        return true;
    }

}
