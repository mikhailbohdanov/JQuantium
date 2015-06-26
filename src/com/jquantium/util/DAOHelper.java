package com.jquantium.util;

import com.jquantium.bean.core.dao.FieldInstance;
import com.jquantium.bean.core.dao.TableInstance;
import com.jquantium.dao.annotations.DAOField;
import com.jquantium.dao.annotations.DAOTable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
public class DAOHelper {
    private static final HashMap<Class, TableInstance> tableInstances = new HashMap<Class, TableInstance>();

    //TODO optimise compiling Instances of tables and rows
    private static void parseClass(Class _class) {
        if (_class == null)
            return;

        Annotation annotation = _class.getAnnotation(DAOTable.class);

        if (annotation == null)
            return;

        DAOTable tableInfo = (DAOTable) annotation;

        TableInstance tableInstance = new TableInstance(tableInfo.value(), tableInfo.engine(), tableInfo.charset(), tableInfo.autoIncrement());

        parseRow(_class, tableInstance);

        tableInstances.put(_class, tableInstance);
    }

    private static void parseRow(Class _class, TableInstance tableInstance) {
        Annotation annotation;
        DAOField fieldInfo;
        FieldInstance fieldInstance;

        for (Field field : _class.getDeclaredFields()) {
            annotation = field.getAnnotation(DAOField.class);

            if (annotation == null) {
                continue;
            }

            fieldInfo = (DAOField) annotation;
            fieldInstance = new FieldInstance(field.getType());

            if (fieldInfo.name().isEmpty()) {
                fieldInstance.setName(field.getName());
            }



            tableInstance.addRow(fieldInstance);
        }
    }

    public static TableInstance get(Object object) {
        if (object != null)
            return get(object.getClass());

        return null;
    }

    public static TableInstance get(Class _class) {
        if (_class != null) {
            if (!tableInstances.containsKey(_class))
                parseClass(_class);

            return tableInstances.get(_class);
        }

        return null;
    }

}
