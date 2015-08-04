package com.jquantium.service;

import com.jquantium.bean.localization.Language;
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
    private AutoList<Language> languageList                 = new AutoList<>();

    @Autowired
    private AutoHashMap<Integer, Language> languageById     = new AutoHashMap<Integer, Language>(languageList) {
        @Override
        public Integer getKey(Language language) {
            return language.getLanguageId();
        }
    };

    @Autowired
    private AutoTreeMap<String, Language> languageByCode;

    public Language getLanguage(int languageId) {
        return languageById.get(languageId);
    }
    public Language getLanguage(String languageCode) {
        return languageByCode.get(languageCode);
    }




}
