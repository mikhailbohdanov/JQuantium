package com.jquantium.bean.core.node;

import com.jquantium.util.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Home on 14.10.2015.
 */
public class Node implements Observable<Node> {
    private List<Consumer> actions = new ArrayList<>();

    protected int nodeId;
    protected String name;
    protected NodeType type;
    protected String url;

    public Node(String name, NodeType type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }
    public Node(int nodeId, String name, NodeType type, String url) {
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

    public NodeType getType() {
        return type;
    }
    public Node setType(NodeType type) {
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
