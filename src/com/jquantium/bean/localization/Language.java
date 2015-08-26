package com.jquantium.bean.localization;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;
import com.jquantium.util.event.Broadcaster;
import com.jquantium.util.event.handlers.Setter;

import javax.interceptor.Interceptors;


/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Table(name = "core_languages")
public class Language implements Broadcaster {

    @Column(primary = true, length = 10, autoIncrement = true, unsigned = true)
    private int languageId;

    @Column(unique = "code", length = 5)
    private String code;

    @Column(notNull = true)
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

    @Interceptors(Setter.class)
    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    @Interceptors(Setter.class)
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
