package com.jquantium.service;

import com.jquantium.bean.localization.Language;
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
    private AutoList<Language> languageList;

    @Autowired
    private AutoTreeMap<String, Language> languageByCode;


}
