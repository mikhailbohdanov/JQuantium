package com.jquantium.bean.core.localization;

import com.jquantium.bean.core.collector.types.CollectorItemInteger;
import com.jquantium.bean.core.collector.types.CollectorItemString;
import com.jquantium.dao.annotations.DAOField;
import com.jquantium.dao.annotations.DAOSetter;
import com.jquantium.dao.annotations.DAOTable;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@DAOTable("")
public class Language implements CollectorItemInteger, CollectorItemString {

    @DAOField(unsigned = true, autoIncrement = true, notNull = true, primary = true)
    private int languageId;

    @DAOField(length = 2, notNull = true, unique = "languageCode")
    private String code;

    @DAOField(length = 255, notNull = true)
    private String name;

    @DAOField
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

    @DAOSetter
    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    @DAOSetter
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public int getKeyInteger() {
        return 0;
    }

    @Override
    public String getKeyString() {
        return null;
    }
}
