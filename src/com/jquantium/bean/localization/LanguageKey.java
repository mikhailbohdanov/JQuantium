package com.jquantium.bean.localization;

/**
 * Created by Mykhailo_Bohdanov on 02/10/2015.
 */
public class LanguageKey {
    private int keyId;
    private String key;
    private String description;

    public LanguageKey(int keyId, String key, String description) {
        this.keyId = keyId;
        this.key = key;
        this.description = description;
    }

    public int getKeyId() {
        return keyId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
