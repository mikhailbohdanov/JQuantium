package com.jquantium.dao.queries;

import com.jquantium.dao.instance.TableInstance;
import com.jquantium.dao.queries.parts.*;
import com.jquantium.helper.DAOHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 03/08/2015.
 */
public class Select {
    private List<Selection> selections  = new ArrayList<>();

    private TableInstance tableFrom;
    private String tableAlias;

    private List<Join> joins            = new ArrayList<>();

    private List<Where> wheres          = new ArrayList<>();

    private List<Order> orders          = new ArrayList<>();

    private List<Group> groups          = new ArrayList<>();

    private Limit limit;

    public Select() {}

    public Select select(String... columns) {
        return select(tableFrom, false, columns);
    }
    public Select select(boolean alias, String... columns) {
        return select(tableFrom, alias, columns);
    }
    public Select select(Object from, boolean alias, String... columns) {
        return select(DAOHelper.getInstance(from), alias, columns);
    }
    public Select select(TableInstance tableInstance, boolean alias, String... columns) {
        if (alias) {
            for (int i = 0; i < columns.length;) {
                selections.add(new Selection(tableInstance.getColumn(columns[i++]), columns[i++]));
            }
        } else {
            for (String column : columns) {
                selections.add(new Selection(tableInstance.getColumn(column)));
            }
        }

        return this;
    }

    public Select from(Class from) {
        this.tableFrom = DAOHelper.getInstance(from);
        return this;
    }

    public Select join() {
        return this;
    }
    public Select where() {
        return this;
    }
    public Select orderBy() {
        return this;
    }
    public Select groupBy() {
        return this;
    }

    public Select limit(int count) {
        this.limit  = new Limit(count);
        return this;
    }
    public Select limit(int offset, int count) {
        this.limit  = new Limit(offset, count);
        return this;
    }

    public String fetch() {
        StringBuilder query = new StringBuilder();

        query.append("SELECT ");

        if (selections != null && selections.size() > 0) {
            for (Selection selection : selections) {
                query
                        .append("`")
                        .append(selection
                                .getColumn()
                                .getTable()
                                .getTableName())
                        .append("`.`")
                        .append(selection
                                .getColumn()
                                .getName())
                        .append("`");

                if (selection.getAlias() != null && !selection.getAlias().isEmpty()) {
                    query
                            .append(" AS `")
                            .append(selection.getAlias())
                            .append("`");
                }

                query.append(" ");
            }
        } else {
            query.append("* ");
        }

        query.append("FROM `").append(tableFrom.getTableName()).append("` ");

        if (tableAlias != null && !tableAlias.isEmpty()) {
            query.append("AS `").append(tableAlias).append("` ");
        }

        return query.toString();
    }
}
