package com.jquantium.service;

import com.jquantium.bean.localization.Language;
import com.jquantium.dao.ORM;
import com.jquantium.util._memory.MemoryHashMap;
import com.jquantium.util._memory.MemoryList;
import com.jquantium.util._memory.MemoryTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Service
public class Localization {
    @Autowired
    private ORM orm;

    private MemoryList<Language> languageList                   = new MemoryList<Language>() {
        @Override
        protected List<Language> init() {
            try {
                return orm.selectList(Language.class);
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
    private MemoryHashMap<Integer, Language> languageById       = new MemoryHashMap<Integer, Language>(languageList) {
        @Override
        public Integer getKey(Language language) {
            return language.getLanguageId();
        }
    };
    private MemoryTreeMap<String, Language> languageByCode      = new MemoryTreeMap<String, Language>(languageList) {
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
