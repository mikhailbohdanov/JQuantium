package com.jquantium.test;

import com.jquantium.bean.localization.Language;
import com.jquantium.util.auto.AutoHashMap;
import com.jquantium.util.auto.AutoList;

/**
 * Created by Mykhailo_Bohdanov on 01/10/2015.
 */
public class Test {

    public static void main(String[] args) {
        AutoList<Language> list = new AutoList<>();

        AutoHashMap<String, Language> map = new AutoHashMap<String, Language>(list) {
            @Override
            public String getKey(Language language) {
                return language.getCode();
            }
        };

        list.create(new Language(1, "ru", "Русский", false));
        list.create(new Language(2, "en", "English", false));

        AutoHashMap<Integer, Language> map1 = new AutoHashMap<Integer, Language>(list) {
            @Override
            public Integer getKey(Language language) {
                return language.getLanguageId();
            }
        };

        System.out.print(list);
        System.out.print(map);
    }

}
