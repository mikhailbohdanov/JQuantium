package com.jquantium.dao.queries.parts;

import com.jquantium.dao.instance.ColumnInstance;

/**
 * Created by Mykhailo_Bohdanov on 06/08/2015.
 */
public class Selection {
    private ColumnInstance column;
    private String alias;

    public Selection(ColumnInstance column) {
        this.column = column;
    }

    public Selection(ColumnInstance column, String alias) {
        this.column = column;
        this.alias = alias;
    }

    public ColumnInstance getColumn() {
        return column;
    }

    public String getAlias() {
        return alias;
    }
}
