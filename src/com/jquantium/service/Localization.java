package com.jquantium.service;

import com.jquantium.bean.localization.Language;
import com.jquantium.dao.ORM;
import com.jquantium.util.auto.AHashMap;
import com.jquantium.util.auto.AList;
import com.jquantium.util.auto.AMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jquantium.util.Assert.EMPTY;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Service
public class Localization {
    @Autowired
    private ORM orm;

    private AList<Language> languageList = new AList<Language>(orm) {
        @Override
        public List<Language> init() {
            try {
                return orm.selectList(Language.class);
            } catch (Exception e) {
                return null;
            }
        }
    };

    private AMap<Integer, Language> languageById = new AHashMap<Integer, Language>(languageList) {
        @Override
        public Integer getKey(Language language) {
            return language.getLanguageId();
        }
    };

    private AMap<String, Language> languageByCode = new AHashMap<String, Language>(languageList) {
        @Override
        public String getKey(Language language) {
            return language.getCode();
        }
    };

    public Language getLanguage(int languageId) throws Exception {
        if (languageId <= 0) {
            throw new Exception();//TODO
        }

        return languageById.get(languageId);
    }

    public Language getLanguage(String code) throws Exception {
        if (EMPTY(code)) {
            throw new Exception();//TODO
        }

        return languageByCode.get(code);
    }

}
