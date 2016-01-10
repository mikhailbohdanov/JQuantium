package com.jquantium.helper;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;
import com.jquantium.dao.instance.ColumnInstance;
import com.jquantium.dao.instance.TableInstance;
import com.jquantium.dao.queries.Select;
import org.springframework.jdbc.core.RowMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Mykhailo_Bohdanov on 02/07/2015.
 */
public class DAOHelper {
    private static HashMap<Class, TableInstance> instances = new HashMap<>();
    private static HashMap<Class, RowMapper> mappers = new HashMap<>();

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

            tField.setAccessible(true);

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

    public static <E> Select<E> getAll(Class<E> eClass) {
        Select<E> select = new Select<>();

        select.from(eClass);

        return select;
    }


    public static <E> RowMapper<E> getMapper(Class<E> eClass) {
        if (!mappers.containsKey(eClass)) {
            mappers.put(eClass, new RowMapper<E>() {
                @Override
                public E mapRow(ResultSet rs, int i) throws SQLException {
                    TableInstance table = getInstance(eClass);
                    ColumnInstance column;
                    ResultSetMetaData metaData = rs.getMetaData();

                    E element = null;

                    try {
                        element = eClass.getConstructor(null).newInstance(null);
                    } catch (Exception e) {}

                    if (element != null) {
                        for (int j = 1; j <= metaData.getColumnCount(); j++) {
                            column = table.getColumn(metaData.getColumnName(j));

                            if (column != null) {
                                if (column.getType().getSuperclass() == Enum.class) {
                                    column.setValue(element, Enum.valueOf(column.getType(), rs.getString(j)));
                                } else {
                                    column.setValue(element, rs.getObject(j, column.getType()));
                                }
                            }
                        }
                    }

                    return element;
                }
            });
        }

        return mappers.get(eClass);
    }
}
