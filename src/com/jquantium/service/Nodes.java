package com.jquantium.service;

import com.jquantium.bean.core.node.DataNode;
import com.jquantium.bean.core.node.Node;
import com.jquantium.bean.core.node.NodeType;
import com.jquantium.dao.DAO;
import com.jquantium.helper.DAOHelper;
import com.jquantium.util.memory.MHashMap;
import com.jquantium.util.memory.MList;
import com.jquantium.util.memory.MTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Home on 14.10.2015.
 */
@Service
public class Nodes {
    @Autowired
    private DAO dao;

    private MList<Node> nodeList                            = new MList<Node>() {
        @Override
        protected List<Node> init() {
            return null;
        }

        @Override
        protected boolean createElement(Node node) {
            MapSqlParameterSource map = new MapSqlParameterSource();

            map
                    .addValue("name", node.getName())
                    .addValue("type", node.getType().name())
                    .addValue("url", node.getUrl());

            try {
                dao.insertRow("INSERT INTO `core_nodes` (`name`, `type`, `url`) VALUES (:name, :type, :url)", map);
            } catch (Exception e) {
                return false;
            }

            return true;
        }

        @Override
        protected boolean updateElement(Node node) {
            MapSqlParameterSource map = new MapSqlParameterSource();

            map
                    .addValue("nodeId", node.getNodeId())
                    .addValue("name", node.getName())
                    .addValue("type", node.getType().name())
                    .addValue("url", node.getUrl());

            try {
                dao.exec("UPDATE `core_nodes` SET `name` = :name, `type` = :type, `url` = :url WHERE `nodeId` = :nodeId", map);
            } catch (Exception e) {
                return false;
            }

            return true;
        }

        @Override
        protected boolean removeElement(Node node) {
            MapSqlParameterSource map = new MapSqlParameterSource();

            map.addValue("nodeId", node.getNodeId());

            try {
                dao.exec("DELETE FROM `core_nodes` WHERE `nodeId` = :nodeId", map);
            } catch (Exception e) {
                return false;
            }

            return true;
        }
    };
    private MHashMap<Integer, Node> nodeById                = new MHashMap<Integer, Node>(nodeList) {
        @Override
        public Integer getKey(Node node) {
            return node.getNodeId();
        }
    };

    private MTreeMap<String, ? super Node> dataNodesByName      = new MTreeMap<String, Node>(nodeList) {
        @Override
        public String getKey(Node node) {
            return node.getName();
        }

        @Override
        protected boolean filter(Node node) {
            switch (node.getType()) {
                case DATA_BASE:
//                    ((DataNode) node).init();//TODO fixme ClassCastException
//                    return true;
                default:
                    return false;
            }
        }
    };

    @Autowired
    public void init(@Qualifier("main") DataSource dataSource) {
        DataNode dataNode = new DataNode("main", NodeType.DATA_BASE, "");
        dataNode.init(dataSource);

        nodeList.add(dataNode);
        dao.setMasterNode(dataNode);

        try {
            nodeList.addAll(dao.selectRows("SELECT * FROM `core_nodes`", null, Node.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Node getNode() {
        return nodeById.get(0);
    }

    public Node getNode(int nodeId) {
        return nodeById.get(nodeId);
    }

    public DataNode getDataNode(String nodeName) {
        return (DataNode) dataNodesByName.get(nodeName);
    }


}
