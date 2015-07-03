package com.jquantium.bean.localization;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;


/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Table(name = "core_languages")
public class Language {

    @Column
    private int languageId;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private boolean enable;

    public Language(int languageId, String code, String name, boolean enable) {
        this.languageId = languageId;
        this.code = code;
        this.name = name;
        this.enable = enable;
    }

    public int getLanguageId() {
        return languageId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
