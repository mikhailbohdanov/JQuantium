package com.jquantium.dao.instance;

import com.jquantium.util.StringTemplate;
import com.jquantium.dao.annotation.Table;

/**
 * Created by Mykhailo_Bohdanov on 02/07/2015.
 */
public class TableInstance<E> {
    private StringTemplate tableName;

    public TableInstance(Table tableInfo, Class tClass) {
        this.tableName = new StringTemplate(tableInfo.name());

    }

    public StringTemplate getTableName() {
        return tableName;
    }


    public void addColumn(ColumnInstance columnInstance) {


    }
}
