package com.jquantium.dao.instance;

import com.jquantium.dao.annotation.Column;

import java.lang.reflect.Field;

/**
 * Created by Mykhailo_Bohdanov on 02/07/2015.
 */
public class ColumnInstance {
    public static ColumnInstance createInstance(Column columnInfo, Field tField) {
        return new ColumnInstance();
    }

    private ColumnInstance() {

    }
}
