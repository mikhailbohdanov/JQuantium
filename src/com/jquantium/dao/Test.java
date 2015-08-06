package com.jquantium.dao;

import com.jquantium.bean.localization.Language;
import com.jquantium.dao.queries.Select;

/**
 * Created by Mykhailo_Bohdanov on 06/08/2015.
 */
public class Test {

    public static void main(String[] args) {
        Select select = new Select();

        String query = select
                .from(Language.class)
                .select("languageId", "code", "name", "enable")
                .fetch();

        System.out.println(query);

    }

}
