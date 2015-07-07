package com.jquantium.helper;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;
import com.jquantium.dao.instance.ColumnInstance;
import com.jquantium.dao.instance.TableInstance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Mykhailo_Bohdanov on 02/07/2015.
 */
public class DAOHelper {
    private static HashMap<Class, TableInstance> instances = new HashMap<>();

    private static void prepareClass(Class tClass) {
        Annotation annotation = tClass.getAnnotation(Table.class);

        if (annotation == null) {
            return;
        }

        TableInstance tableInstance = new TableInstance((Table) annotation, tClass);

        if (tableInstance == null) {
            return;
        }

        ColumnInstance columnInstance;

        for (Field tField : tClass.getDeclaredFields()) {
            annotation = tField.getAnnotation(Column.class);

            if (annotation == null) {
                continue;
            }

            columnInstance = new ColumnInstance((Column) annotation, tField);

            if (columnInstance == null) {
                continue;
            }

            tableInstance.addColumn(columnInstance);
        }

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
