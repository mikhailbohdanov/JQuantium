package com.jquantium.service;

import com.jquantium.bean.localization.Language;
import com.jquantium.bean.localization.LanguageKey;
import com.jquantium.dao.ORM;
import com.jquantium.util.auto.AutoHashMap;
import com.jquantium.util.auto.AutoList;
import com.jquantium.util.auto.AutoTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Service
public class Localization {
    @Autowired
    private ORM orm;

    private AutoList<Language> languageList                     = new AutoList<>();
    private AutoHashMap<Integer, Language> languageById         = new AutoHashMap<Integer, Language>(languageList) {
        @Override
        public Integer getKey(Language language) {
            return language.getLanguageId();
        }
    };
    private AutoTreeMap<String, Language> languageByCode        = new AutoTreeMap<String, Language>(languageList) {
        @Override
        public String getKey(Language language) {
            return language.getCode();
        }
    };

    private AutoList<LanguageKey> languageKeys                  = new AutoList<>();
    private AutoHashMap<Integer, LanguageKey> languageKeysById  = new AutoHashMap<Integer, LanguageKey>(languageKeys) {
        @Override
        public Integer getKey(LanguageKey key) {
            return key.getKeyId();
        }
    };
    private AutoTreeMap<String, LanguageKey> languageKeysByKey  = new AutoTreeMap<String, LanguageKey>(languageKeys) {
        @Override
        public String getKey(LanguageKey key) {
            return key.getKey();
        }
    };

    public boolean hasLanguage(int languageId) {
        return languageById.containsKey(languageId);
    }
    public boolean hasLanguage(String code) {
        return languageByCode.containsKey(code);
    }

    public void createLanguage(String code, String name) {
        Language language = new Language(0, code, name, false);

        languageList.create(language);
    }

    public Language getLanguage(int languageId) {
        return languageById.get(languageId);
    }
    public Language getLanguage(String languageCode) {
        return languageByCode.get(languageCode);
    }



}
