package com.jquantium.bean.localization;

import com.jquantium.dao.annotation.Column;
import com.jquantium.dao.annotation.Table;
import com.jquantium.util.memory.MList;
import com.jquantium.util.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Mykhailo_Bohdanov on 26/06/2015.
 */
@Table(name = "core_languages")
public class Language implements Observable {
    private class KeyList extends MList<LanguageValue> {

        @Override
        protected List<LanguageValue> init() {
            return null;
        }

        @Override
        protected boolean createElement(LanguageValue element) {
            return false;
        }

        @Override
        protected boolean updateElement(LanguageValue element) {
            return false;
        }

        @Override
        protected boolean removeElement(LanguageValue element) {
            return false;
        }
    }

    private List<Consumer> actions = new ArrayList<>();

    private KeyList valueList;

    @Column(primary = true, length = 10, autoIncrement = true, unsigned = true)
    private int languageId;

    @Column(unique = "code", length = 5)
    private String code;

    @Column(notNull = true)
    private String name;

    @Column
    private boolean enabled;

    public Language(int languageId, String code, String name, boolean enabled) {
        this.languageId = languageId;
        this.code = code;
        this.name = name;
        this.enabled = enabled;

        if (languageId > 0) {
            valueList = new KeyList();
        }
    }

    public int getLanguageId() {
        return languageId;
    }

    public String getCode() {
        return code;
    }
    public Language setCode(String code) {
        this.code = code;

        fireEvent();

        return this;
    }

    public String getName() {
        return name;
    }
    public Language setName(String name) {
        this.name = name;

        fireEvent();

        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public Language setEnabled(boolean enabled) {
        this.enabled = enabled;

        fireEvent();

        return this;
    }

    public String getWord(String key) {
        return null;
    }

    @Override
    public void register(Consumer action) {
        if (action != null) {
            actions.add(action);
        }
    }

    @Override
    public void unregister(Consumer action) {
        if (action != null) {
            actions.remove(action);
        }
    }

    @Override
    public void fireEvent() {
        for (Consumer action : actions) {
            action.accept(this);
        }
    }
}
