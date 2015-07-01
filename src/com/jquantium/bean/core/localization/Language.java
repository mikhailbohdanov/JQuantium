package com.jquantium.bean.core.localization;

import com.jquantium.bean.core.dao.Column;
import com.jquantium.bean.core.dao.Entity;
import com.jquantium.bean.core.dao.Id;
import com.jquantium.bean.core.dao.Table;


/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Entity
@Table(name = "core_languages")
public class Language {

    @Id
    @Column
    private int languageId;

    private String code;

    private String name;

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
