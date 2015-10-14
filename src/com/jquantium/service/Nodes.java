package com.jquantium.service;

import com.jquantium.bean.core.node.DataNode;
import com.jquantium.bean.core.node.Node;
import com.jquantium.bean.core.node.NodeType;
import com.jquantium.dao.ORM;
import com.jquantium.util.memory.MHashMap;
import com.jquantium.util.memory.MList;
import com.jquantium.util.memory.MMap;
import com.jquantium.util.memory.MTreeMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 14.10.2015.
 */
public class Nodes {
    @Autowired
    private ORM orm;

    private MList<Node> nodeList                    = new MList<Node>() {
        @Override
        protected List init() {
            List<Node> list = new ArrayList<>();

            list.add(new Node("main", NodeType.DATA_BASE, ""));

            return list;
        }
    };
    private MMap<Integer, Node> nodeById            = new MHashMap<Integer, Node>(nodeList) {
        @Override
        public Integer getKey(Node node) {
            return node.getNodeId();
        }
    };

    private MMap<String, DataNode> dataNodesByName  = new MTreeMap<String, DataNode>(nodeList) {
        @Override
        public String getKey(DataNode node) {
            return node.getName();
        }

        @Override
        protected boolean condition(DataNode node) {
            switch (node.getType()) {
                case DATA_BASE:
                    node.init();
                    return true;
                default:
                    return false;
            }
        }
    };

}
