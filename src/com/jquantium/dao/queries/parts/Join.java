package com.jquantium.dao.queries.parts;

import com.jquantium.dao.instance.TableInstance;

/**
 * Created by Mykhailo_Bohdanov on 06/08/2015.
 */
public class Join {
    public enum TYPE {
        LEFT,
        RIGHT
    }

    private TableInstance join;
    private String alias;

    private TYPE type;


}
