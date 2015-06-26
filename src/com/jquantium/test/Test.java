package com.jquantium.test;

import com.jquantium.bean.core.localization.Language;
import com.jquantium.util.DAOHelper;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
public class Test {

    public static void main(String[] args) {
        DAOHelper.get(Language.class);
    }
}
