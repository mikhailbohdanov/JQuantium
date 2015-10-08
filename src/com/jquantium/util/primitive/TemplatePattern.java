package com.jquantium.util.primitive;

import com.jquantium.util.Primitives;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by FREEMAN on 10.02.15.
 */
public class TemplatePattern extends Template {
    private Pattern pattern;

    private TemplatePattern() {
        super();
    }
    public TemplatePattern(String original) {
        super(original);

        if (isTemplate) {
            StringBuilder pattern   = new StringBuilder(original.replaceAll("([\\(\\)\\[\\]\\/.^$|?+]{1,1})", "\\\\$0"));

            for (Map.Entry<String, Integer> entry : keys.entrySet()) {
                Primitives.replaceAll(pattern, left + entry.getKey() + right, "(.*}");
            }

            pattern.insert(0, "^").append("$");
            this.pattern    = Pattern.compile(pattern.toString());
        }
    }

    public boolean match(Object object) {
        if (object == null) {
            return false;
        }

        if (!isTemplate) {
            return original.equals(object);
        }

        return pattern.matcher(object.toString()).matches();
    }

    public HashMap<String, String> getValues(Object source) {
        if (source == null) {
            return null;
        }

        Matcher matcher = this.pattern.matcher(source.toString());

        HashMap<String, String> keys = null;
        if (matcher.matches()) {
            int i = 1;

            keys   = new HashMap<>();

            for (Map.Entry<String, Integer> entry : super.keys.entrySet()) {
                keys.put(entry.getKey(), matcher.group(i++));
            }
        }

        return keys;
    }
}
