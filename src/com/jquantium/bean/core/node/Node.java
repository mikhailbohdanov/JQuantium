package com.jquantium.bean.core.node;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;
import com.jquantium.util.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Home on 14.10.2015.
 */
@Table(name = "core_nodes")
public class Node implements Observable<Node> {
    public enum Type {
        DATA_BASE,
        FILE_STORAGE,
        MAIL_SENDER
    }

    private List<Consumer> actions = new ArrayList<>();

    @Column(primary = true, length = 10, unsigned = true, autoIncrement = true)
    protected int nodeId;

    @Column(unique = "name", length = 64, notNull = true)
    protected String name;

    @Column
    protected Type type;

    @Column(length = 0)
    protected String url;

    public Node() {}
    public Node(String name, Type type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }
    public Node(int nodeId, String name, Type type, String url) {
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

    public Type getType() {
        return type;
    }
    public Node setType(Type type) {
        this.type = type;
        fireEvent();
        return this;
    }

    public String getUrl() {
        return url;
    }
    public Node setUrl(String url) {
        this.url = url;
        fireEvent();
        return this;
    }

    @Override
    public void register(Consumer action) {
        if (action != null) {
            actions.add(action);
        }
    }

    @Override
    public void unregister(Consumer action) {
        if (action != null) {
            actions.remove(action);
        }
    }

    @Override
    public void fireEvent() {
        for (Consumer action : actions) {
            action.accept(this);
        }
    }
}
