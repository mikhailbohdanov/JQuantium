package com.jquantium.bean.core;

import com.jquantium.bean.Url;
import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;
import com.jquantium.util.observer.Observable;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 13.10.2015.
 */
@Table(name = "core_nodes")
public class DataNode implements Observable {
    List<Observer<DataNode>> observers = new ArrayList<>();

    @Column(primary = true, length = 10, autoIncrement = true, unsigned = true)
    private int nodeId;

    @Column(unique = "name", length = 64, notNull = true)
    private String name;

    @Column(notNull = true)
    private DataNodeType type;

    @Column(length = 0, notNull = true)
    private String url;

    private Url _url;
    private MysqlDataSource dataSource;
    private NamedParameterJdbcTemplate jdbc;

    public DataNode(int nodeId, String name, DataNodeType type, Url url) {
        this.nodeId = nodeId;
        this.name = name;
        this.type = type;
        this.url = url;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getName() {
        return name;
    }

    public DataNodeType getType() {
        return type;
    }
    public DataNode setType(DataNodeType type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return url;
    }
    public DataNode setUrl(String url) {
        this.url = url;
        return this;
    }

    public boolean init() {
        if (type != null && url != null && !url.isEmpty()) {
            _url = new Url(url);

            switch (type) {
                case CORE:
                    break;
                case DATA:
                    dataSource = new MysqlDataSource();

                    dataSource.setUrl(url);
                    dataSource.setUser(_url.getUserName());
                    dataSource.setPassword(_url.getPassword());

                    break;
                case FILE:
                    break;
                case MAIL:
                    break;
            }
        }

        return false;
    }

    @Override
    public void register(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    @Override
    public void unregister(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }

    @Override
    public void fireEvent() {
        for (Observer observer : observers) {
            observer.handleEvent(this);
        }
    }
}
