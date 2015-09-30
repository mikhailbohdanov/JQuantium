package com.jquantium.test;

import com.jquantium.bean.localization.Language;
import com.jquantium.util.collections.auto.AutoHashMap;
import com.jquantium.util.collections.auto.AutoList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 31/08/2015.
 */
public class TestLang {
    private AutoList<Language> languages = new AutoList<>();
    private AutoHashMap<Integer, Language> languagesById = new AutoHashMap<Integer, Language>(languages) {
        @Override
        public Integer getKey(Language element) {
            return element.getLanguageId();
        }
    };
    private AutoHashMap<String, Language> languagesByCode = new AutoHashMap<String, Language>(languages) {
        @Override
        public String getKey(Language element) {
            return element.getCode();
        }
    };

    public static void main(String[] args) {
        TestLang test = new TestLang();

        test.languages.add(new Language(1, "ru", "Русский", true));
        test.languages.add(new Language(2, "en", "English", true));

        test.languagesByCode.put(new Language(3, "ua", "Украинский", false));

        System.out.println(test.languagesById);
        System.out.println(test.languagesByCode);
    }


}