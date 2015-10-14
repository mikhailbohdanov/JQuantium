package com.jquantium.bean.view.page;

import com.jquantium.helper.ObjectHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * Created by Mykhailo_Bohdanov on 01/07/2015.
 */
public class Component {
    private int componentId;
    private String name;
    private JSONObject config;

    public Component(int componentId, String name, String config) {
        this.componentId = componentId;
        this.name = name;
        this.setConfig(config);
    }

    public int getComponentId() {
        return componentId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getConfig(String key) {
        if (config == null || !config.containsKey(key)) {
            return null;
        }

        return (String) config.get(key);
    }
    public JSONObject getConfigAsJSONO(String key) {
        if (config == null || !config.containsKey(key)) {
            return null;
        }

        return (JSONObject) config.get(key);
    }
    public JSONArray getConfigAsJSONA(String key) {
        if (config == null || !config.containsKey(key)) {
            return null;
        }

        return (JSONArray) config.get(key);
    }
    public Object getConfigAsObject(String key) {
        if (config == null || !config.containsKey(key)) {
            return null;
        }

        return config.get(key);
    }
    public String getConfigAsString() {
        return config.toJSONString();
    }
    public Component setConfig(String config) {
        try {
            this.config = (JSONObject) ObjectHelper.JSONParser.parse(config);
        } catch (ParseException e) {}

        return this;
    }
}
