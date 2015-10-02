package com.jquantium.service;

import com.jquantium.bean.localization.Language;
import com.jquantium.bean.localization.LanguageKey;
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

    private AutoList<Language> languageList                 = new AutoList<>();

    private AutoHashMap<Integer, Language> languageById     = new AutoHashMap<Integer, Language>(languageList) {
        @Override
        public Integer getKey(Language language) {
            return language.getLanguageId();
        }
    };

    private AutoTreeMap<String, Language> languageByCode    = new AutoTreeMap<String, Language>(languageList) {
        @Override
        public String getKey(Language language) {
            return language.getCode();
        }
    };

    private AutoList<LanguageKey> languageKeys              = new AutoList<>();


    public Language getLanguage(int languageId) {
        return languageById.get(languageId);
    }
    public Language getLanguage(String languageCode) {
        return languageByCode.get(languageCode);
    }




}
