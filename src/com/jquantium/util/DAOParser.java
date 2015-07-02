package com.jquantium.util;

import com.jquantium.dao.annotation.Table;
import com.jquantium.dao.instance.TableInstance;

import java.lang.annotation.Annotation;
import java.util.HashMap;

/**
 * Created by Mykhailo_Bohdanov on 02/07/2015.
 */
public class DAOParser {
    private static HashMap<Class, TableInstance> instances = new HashMap<>();

    private static void prepareClass(Class tClass) {
        Annotation annotation = tClass.getAnnotation(Table.class);

        if (annotation == null) {
            return;
        }

        Table tableInfo = (Table) annotation;

        TableInstance tableInstance = new TableInstance(tableInfo);






        instances.put(tClass, tableInstance);
    }

    public static TableInstance getInstance(Object object) {
        if (object == null) {
            return null;
        }

        return getInstance(object.getClass());
    }
    public static TableInstance getInstance(Class tClass) {
        if (tClass == null) {
            return null;
        }

        if (!instances.containsKey(tClass)) {
            prepareClass(tClass);
        }

        return instances.get(tClass);
    }

}
