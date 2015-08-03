package com.jquantium.service;

import com.jquantium.bean.localization.Language;
import com.jquantium.util.auto.AutoHashMap;
import com.jquantium.util.auto.AutoList;
import com.jquantium.util.auto.AutoTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> acf2d1c9ee0b2a6c3291f7481fa7ca0c990bc298

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Service
public class Localization {

    @Autowired
    private AutoList<Language> languageList;

    @Autowired
    private AutoHashMap<Integer, Language> languageById;

    @Autowired
    private AutoTreeMap<String, Language> languageByCode;

    public Language getLanguage(int languageId) {
        return languageById.get(languageId);
    }
    public Language getLanguage(String languageCode) {
        return languageByCode.get(languageCode);
    }




}
