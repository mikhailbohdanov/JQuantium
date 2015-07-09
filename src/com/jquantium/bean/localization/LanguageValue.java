package com.jquantium.bean.localization;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;

/**
 * Created by Mykhailo_Bohdanov on 03/07/2015.
 */
@Table(name = "core_languages_values")
public class LanguageValue {

    @Column(primary = true, length = 10, autoIncrement = true, unsigned = true)
    private int valueId;

    @Column(unique = "language-key", length = 10, unsigned = true)
    private int languageId;

    @Column(unique = "language-key", length = 10, unsigned = true)
    private int keyId;

    @Column(length = 4096)
    private String value;

}
