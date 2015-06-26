package com.jquantium.bean.core.dao;

import com.jquantium.bean.core.StringTemplate;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
public class FieldInstance<T> {
    private Class<T> tClass;

    private String name;
    private boolean notNull;
    private String defaultValue;
    private boolean primary;
    private String unique;

    // - For strings and numbers
    private int length;

    // - For numbers
    private boolean unsigned;
    private boolean autoIncrement;

    public FieldInstance(Class<T> tClass) {
        this.tClass = tClass;
    }

    public Class<T> gettClass() {
        return tClass;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isNotNull() {
        return notNull;
    }
    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isPrimary() {
        return primary;
    }
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isUnique() {
        return unique != null && !unique.isEmpty();
    }
    public String getUnique() {
        return unique;
    }
    public void setUnique(String unique) {
        this.unique = unique;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public boolean isUnsigned() {
        return unsigned;
    }
    public void setUnsigned(boolean unsigned) {
        this.unsigned = unsigned;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }
    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
}
