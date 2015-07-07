package com.jquantium.dao.instance;

import com.jquantium.dao.annotation.Column;

import java.lang.reflect.Field;

/**
 * Created by Mykhailo_Bohdanov on 02/07/2015.
 */
public class ColumnInstance {
    private Field tField;

    private String name;

    private boolean primary;
    private String unique;

    private int length;
    private String defaultValue;
    private boolean notNull;

    private boolean unsigned;
    private boolean autoIncrement;

    public ColumnInstance(Column columnInfo, Field tField) {
        this.tField         = tField;

        this.name           = columnInfo.name();
        this.primary        = columnInfo.primary();
        this.unique         = columnInfo.unique();
        this.length         = columnInfo.length();
        this.defaultValue   = columnInfo.defaultValue();
        this.notNull        = columnInfo.notNull();
        this.unsigned       = columnInfo.unsigned();
        this.autoIncrement  = columnInfo.autoIncrement();
    }

    public String getName() {
        return name;
    }

    public boolean isPrimary() {
        return primary;
    }
    public String getUnique() {
        return unique;
    }

    public int getLength() {
        return length;
    }
    public String getDefaultValue() {
        return defaultValue;
    }
    public boolean isNotNull() {
        return notNull;
    }

    public boolean isUnsigned() {
        return unsigned;
    }
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

}
