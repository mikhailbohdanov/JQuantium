package com.jquantium.test;

import com.jquantium.bean.Url;
import com.jquantium.bean.core.DataNode;
import com.jquantium.bean.core.DataNodeType;
import com.jquantium.util._memory.MemoryList;

import java.util.List;

/**
 * Created by Home on 13.10.2015.
 */
public class Test {

    public static void main(String[] args) {

        DataNode dataNode = new DataNode(0, "main", DataNodeType.DATA, new Url());

        MemoryList<DataNode> list = new MemoryList<DataNode>() {
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

        list.add(dataNode);

        dataNode.fireEvent();
    }

}
