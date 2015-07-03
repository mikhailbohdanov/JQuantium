package com.jquantium.dao.instance;

import com.jquantium.bean.core.StringTemplate;
import com.jquantium.dao.annotation.Table;

/**
 * Created by Mykhailo_Bohdanov on 02/07/2015.
 */
public class TableInstance {
    public static TableInstance createInstance(Table tableInfo, Class tClass) {
        TableInstance tableInstance = new TableInstance();

        tableInstance.tableName = new StringTemplate(tableInfo.name());

        return tableInstance;
    }

    private StringTemplate tableName;

    private TableInstance() {}

    public StringTemplate getTableName() {
        return tableName;
    }


    public void addColumn(ColumnInstance columnInstance) {

    }
}
