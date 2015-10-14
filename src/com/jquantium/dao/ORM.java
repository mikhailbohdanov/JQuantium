package com.jquantium.dao;

import com.jquantium.bean.core.node.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 26/08/2015.
 */
//@Repository
public class ORM {
//    @Autowired
    private DAO dao;

    public int insert(Object object) throws Exception {
        return insert(object, 0);
    }
    public int insert(Object object, int nodeId) throws Exception {
        return 0;
    }

    public int[] insert(Object[] objects) throws Exception {
        return insert(objects, 0);
    }
    public int[] insert(Object[] objects, int nodeId) throws Exception {
        int[] returns = new int[objects.length];

        for (int i = 0; i < objects.length; i++) {
            returns[i] = insert(objects[i], nodeId);
        }

        return returns;
    }

    public int[] insert(List<Object> elements) throws Exception {
        return insert(elements, 0);
    }
    public int[] insert(List<Object> elements, int nodeId) throws Exception {
        int[] returns = new int[elements.size()];

        for (int i = 0; i < elements.size(); i++) {
            returns[i] = insert(elements.get(i), nodeId);
        }

        return returns;
    }

    public <E> E selectById(Class<E> eClass, int id) throws Exception {
        return selectById(eClass, id, 0);
    }
    public <E> E selectById(Class<E> eClass, int id, int nodeId) throws Exception {
        return null;
    }

    public <E> List<E> selectList(Class<E> eClass) throws Exception {
        return selectList(eClass, 0);
    }
    public <E> List<E> selectList(Class<E> eClass, int nodeId) throws Exception {
        if (eClass == Node.class) {
            return dao.getRowList("SELECT * FROM `core_nodes`", null, eClass);
        } else {
            return null;
        }
    }

    public boolean update(Object object) throws Exception {
        return update(object, 0);
    }
    public boolean update(Object object, int nodeId) throws Exception {
        return false;
    }

    public boolean delete(Object object) throws Exception {
        return delete(object, 0);
    }
    public boolean delete(Object object, int nodeId) throws Exception {
        return false;
    }

}
