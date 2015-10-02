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

    public String fetchSelect() {
        return fetchSelect(new StringBuilder()).toString();
    }
    public StringBuilder fetchSelect(StringBuilder out) {
        out
                .append("`")
                .append(column.getTable().getTableName())
                .append("`.`")
                .append(column.getName())
                .append("`");

        if (alias != null && !alias.isEmpty()) {
            out
                    .append(" AS `")
                    .append(alias)
                    .append("`");
        }

        return out;
    }

    public String fetchWhere() {
        return fetchWhere(new StringBuilder()).toString();
    }
    public StringBuilder fetchWhere(StringBuilder out) {
        out
                .append("`")
                .append(column.getTable().getTableName())
                .append("`.`")
                .append(column.getName())
                .append("`");

        return out;
    }



}
