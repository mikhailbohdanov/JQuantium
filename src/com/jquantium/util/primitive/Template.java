package com.jquantium.util.primitive;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Mykhailo_Bohdanov on 08/10/2015.
 */
public class Template {
    protected static final String left          = "{";
    protected static final String right         = "}";
    protected static final int leftLength       = left.length();
    protected static final int rightLength      = right.length();
    protected static final Pattern keyPattern   = Pattern.compile("[a-zA-Z0-9]+");

    protected String original;
    protected LinkedHashMap<String, Integer> keys;
    protected StringBuilder template;

    protected boolean isTemplate                = false;

    protected Template() {}

    public Template(String original) {
        if (original == null || original.isEmpty()) {
            original        = "";
        } else {


            StringBuilder source                = new StringBuilder(original);
            LinkedHashMap<String, Integer> keys = new LinkedHashMap<>();

            String key;
            int start, end, offset = 0;
            while ((start = source.indexOf(left, offset)) >= 0) {
                end     = source.indexOf(right, start + leftLength);
                key     = source.substring(start + leftLength, end);

                if (keyPattern.matcher(key).matches()) {
                    keys.put(key, start);
                    source.replace(start, end + rightLength, "");
                } else {
                    offset = end;
                }
            }

            if (keys.size() > 0) {
                this.isTemplate = true;
                this.keys       = keys;
                this.template   = source;
            }
        }

        this.original       = original;
    }

    @Override
    public String toString() {
        if (!isTemplate) {
            return original;
        }

        return template.toString();
    }

    public String toStringObject(Object object) {
        if (!isTemplate) {
            return original;
        }

        if (object == null) {
            return template.toString();
        }

        StringBuilder out   = new StringBuilder(template);

        Class _class = object.getClass();

        Field field;
        String key, value;
        int offset  = 0;
        for (Map.Entry<String, Integer> entry : this.keys.entrySet()) {
            key = entry.getKey();

            try {
                field   = _class.getDeclaredField(key);

                field.setAccessible(true);
                value   = String.valueOf(field.get(object));
                field.setAccessible(false);

                out.insert(entry.getValue() + offset, value);
                offset  += value.length();
            } catch (NoSuchFieldException e) {
            } catch (IllegalAccessException e) {
            }
        }

        return out.toString();
    }

    public String toString(Map keys) {
        if (!isTemplate) {
            return original;
        }

        if (keys == null) {
            return template.toString();
        }

        StringBuilder out = new StringBuilder(template);

        int offset  = 0;
        String key;
        Object data;
        for (Map.Entry<String, Integer> entry : this.keys.entrySet())
            if (keys.containsKey(key = entry.getKey())) {
                data    = keys.get(key);

                out.insert(entry.getValue() + offset, data);

                offset  += (String.valueOf(data)).length();
            }

        return out.toString();
    }
    public String toString(String... elements) {
        if (!isTemplate) {
            return original;
        }

        if (elements == null) {
            return template.toString();
        }

        StringBuilder out = new StringBuilder(template);

        int offset  = 0;
        int i       = 0;
        Object data;
        for (Map.Entry<String, Integer> entry : keys.entrySet()) {
            data    = elements[i++];

            out.insert(entry.getValue() + offset, data);

            offset  += String.valueOf(data).length();

            if (i >= elements.length) {
                i = 0;
            }
        }

        return out.toString();
    }
    public String toString(Integer... elements) {
        if (!isTemplate) {
            return original;
        }

        if (elements == null) {
            return template.toString();
        }

        String[] _elements = new String[elements.length];

        for (int i = 0; i < elements.length; i++)
            _elements[i] = String.valueOf(elements[i]);

        return toString(_elements);
    }
}
