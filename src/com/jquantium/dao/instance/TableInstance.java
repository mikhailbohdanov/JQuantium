package com.jquantium.dao.instance;

import com.jquantium.dao.annotation.Table;
import com.jquantium.util.primitive.Template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Mykhailo_Bohdanov on 02/07/2015.
 */
public class TableInstance<E> {
    private Template tableName;
    
    private List<ColumnInstance> columns                = new ArrayList<>();
    private Map<String, ColumnInstance> columnsByName   = new TreeMap<>();

    public TableInstance(Table tableInfo, Class tClass) {
        this.tableName = new Template(tableInfo.name());
    }

    public Template getTableName() {
        return tableName;
    }

    public void addColumn(ColumnInstance columnInstance) {
        columnInstance.setTable(this);

        columns.add(columnInstance);
        columnsByName.put(columnInstance.getName(), columnInstance);
    }

    public List<ColumnInstance> getColumns() {
        return columns;
    }
    public ColumnInstance getColumn(String columnName) {
        return columnsByName.get(columnName);
    }

}
