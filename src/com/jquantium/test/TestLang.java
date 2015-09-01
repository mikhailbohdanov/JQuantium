package com.jquantium.test;

import com.jquantium.bean.localization.Language;
import com.jquantium.util.auto.AutoHashMap;
import com.jquantium.util.auto.AutoList;
import com.jquantium.util.event.Broadcaster;
import com.jquantium.util.event.Watcher;

/**
 * Created by Mykhailo_Bohdanov on 31/08/2015.
 */
public class TestLang {

    public static void main(String[] args) {
        AutoList<Language> languages = new AutoList<>();
        AutoHashMap<Integer, Language> languagesById = new AutoHashMap<Integer, Language>(languages) {
            @Override
            public Integer getKey(Language element) {
                return element.getLanguageId();
            }
        };
        AutoHashMap<String, Language> languagesByCode = new AutoHashMap<String, Language>(languages) {
            @Override
            public String getKey(Language element) {
                return element.getCode();
            }
        };

        languages.add(new Language(1, "ru", "Русский", true));
        languages.add(new Language(2, "en", "English", true));

        System.out.println(languagesById);
        System.out.println(languagesByCode);

    }

}
