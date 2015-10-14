package com.jquantium.service;

import com.jquantium.bean.localization.Language;
import com.jquantium.dao.ORM;
import com.jquantium.util.memory.MHashMap;
import com.jquantium.util.memory.MList;
import com.jquantium.util.memory.MTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Service
public class Localization {
//    @Autowired
//    private ORM orm;

    private MList<Language> languageList                = new MList<Language>() {
        @Override
        protected List<Language> init() {
            try {
                return null;//orm.selectList(Language.class);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected boolean createElement(Language element) {
            return false;
        }

        @Override
        protected boolean updateElement(Language element) {
            return false;
        }

        @Override
        protected boolean removeElement(Language element) {
            return false;
        }
    };
    private MHashMap<Integer, Language> languageById    = new MHashMap<Integer, Language>(languageList) {
        @Override
        public Integer getKey(Language language) {
            return language.getLanguageId();
        }
    };
    private MTreeMap<String, Language> languageByCode   = new MTreeMap<String, Language>(languageList) {
        @Override
        public String getKey(Language language) {
            return language.getCode();
        }
    };



    public boolean hasLanguage(int languageId) {
        return languageById.containsKey(languageId);
    }
    public boolean hasLanguage(String code) {
        return languageByCode.containsKey(code);
    }

    public Language getLanguage(int languageId) {
        return languageById.get(languageId);
    }
    public Language getLanguage(String languageCode) {
        return languageByCode.get(languageCode);
    }


}
