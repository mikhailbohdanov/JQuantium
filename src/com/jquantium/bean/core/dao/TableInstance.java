package com.jquantium.bean.core.dao;

import com.jquantium.bean.core.StringTemplate;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
public class TableInstance {
    private StringTemplate tableName;
    private String engine;
    private String charset;
    private int autoIncrement;

    private ArrayList<FieldInstance> rows                     = new ArrayList<>();
    private ArrayList<FieldInstance> primary                  = new ArrayList<>();
    private TreeMap<String, ArrayList<FieldInstance>> unique  = new TreeMap<>();

    public TableInstance(StringTemplate tableName, String engine, String charset, int autoIncrement) {
        this.tableName = tableName;
        this.engine = engine;
        this.charset = charset;
        this.autoIncrement = autoIncrement;
    }
    public TableInstance(String tableName, String engine, String charset, int autoIncrement) {
        this(new StringTemplate(tableName), engine, charset, autoIncrement);
    }

    public StringTemplate getTableName() {
        return tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public int getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(int autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public void addRow(FieldInstance row) {
        rows.add(row);

        if (row.isPrimary()) {
            primary.add(row);
        }

        if (row.isUnique()) {
            unique.put(row.getUnique(), rows);
        }
    }
}
