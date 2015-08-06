package com.jquantium.dao.queries.parts;

/**
 * Created by Mykhailo_Bohdanov on 06/08/2015.
 */
public class Limit {
    private int offset;
    private int count;

    public Limit(int count) {
        this.count = count;
    }

    public Limit(int offset, int count) {
        this.offset = offset;
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }
}
