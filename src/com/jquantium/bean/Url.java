package com.jquantium.bean;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Михаил on 26.08.14.
 */
public final class Url {
    public static String URLElementDecode(String element) {
        try {
            return URLDecoder.decode(element, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static String URLEncodeEncode(String element) {
        try {
            return URLEncoder.encode(element, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static Map<String, List<String>> parseSearch(String search) {
        Map<String, List<String>> parsedData = new TreeMap<>();

        String[] searchParts = search.split("&"), keyValue;
        String key;
        List<String> values;

        for (String part : searchParts) {
            keyValue = part.split("=");

            key = keyValue[0].replaceAll("\\[.*\\]$", "");

            if (!parsedData.containsKey(key)) {
                values = new ArrayList<>();

                parsedData.put(key, values);
            } else {
                values = parsedData.get(key);
            }

            values.add(URLElementDecode(keyValue[1]));
        }

        return parsedData;
    }

    private String protocol;
    private String userName;
    private String password;
    private String host;
    private int port;
    private String path;
    private Map<String, List<String>> search;
    private String hash;

    public Url() {}
    public Url(String pattern) {
        parse(pattern, this);
    }
    public Url(HttpServletRequest request) {
        this.path = request.getRequestURI().replaceAll("/$", "");

        if (this.path.isEmpty()) {
            this.path = "/";
        }

        List<String> values;
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            values = new ArrayList<>();

            for (String value : entry.getValue()) {
                values.add(value);
            }

            this.setSearch(entry.getKey(), values);
        }
    }

    public static Url parse(String urlString) {
        return parse(urlString, new Url());
    }
    public static Url parse(String urlString, Url context) {
        String tmp;
        int index;
        String[] vars;

        if (context == null) {
            context = new Url();
        }

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {}

        if (url != null) {
            context.setProtocol(url.getProtocol());

            if ((tmp = url.getUserInfo()) != null) {
                vars    = tmp.split(":");

                if (vars.length > 1) {
                    context.setUserName(vars[0]);
                    context.setPassword(vars[1]);
                } else {
                    context.setUserName(vars[0]);
                }
            }

            context.setHost(url.getHost());

            context.setPort(url.getPort());

            context.setPath(url.getPath());

            context.setSearch(url.getQuery());

            context.setHash(url.getRef());
        }

        return context;
    }

    public String getProtocol() {
        return protocol;
    }
    public Url setProtocol(String protocol) {
        if (protocol == null || protocol.isEmpty()) {
            this.protocol = null;
        } else {
            this.protocol = protocol;
        }

        return this;
    }

    public String getUserName() {
        return userName;
    }
    public Url setUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            this.userName = null;
        } else {
            this.userName = userName;
        }

        return this;
    }

    public String getPassword() {
        return password;
    }
    public Url setPassword(String password) {
        if (password == null || password.isEmpty()) {
            this.password = null;
        } else {
            this.password = password;
        }

        return this;
    }

    public String getHost() {
        return host;
    }
    public Url setHost(String host) {
        if (host == null || host.isEmpty()) {
            this.host = null;
        } else {
            this.host = host;
        }

        return this;
    }

    public int getPort() {
        return port;
    }
    public Url setPort(int port) {
        this.port = port;

        return this;
    }

    public String getPath() {
        return path;
    }
    public Url setPath(String path) {
        if (path == null || path.isEmpty()) {
            this.path = null;
        } else {
            this.path = path;
        }

        return this;
    }

    public Map<String, List<String>> getSearch() {
        return search;
    }
    public List<String> getSearch(String searchKey) {
        if (searchKey == null) {
            return null;
        }

        return search.get(searchKey);
    }
    public String getSearch(String searchKey, int index) {
        if (searchKey == null || search == null || !search.containsKey(searchKey)) {
            return null;
        }

        return search.get(searchKey).get(index);
    }

    public Url setSearch(String search) {
        if (search == null || search.isEmpty()) {
            this.search = null;
        } else {
            this.search = parseSearch(search);
        }

        return this;
    }
    public Url addSearch(String search) {
        if (search != null || !search.isEmpty()) {
            addSearch(parseSearch(search));
        }

        return this;
    }

    public Url setSearch(Map<String, List<String>> searchData) {
        if (searchData == null) {
            this.search = null;
        } else {
            this.search = searchData;
        }

        return this;
    }
    public Url addSearch(Map<String, List<String>> searchData) {
        if (searchData == null) {
            this.search = null;
        } else {
            if (this.search == null) {
                this.search = searchData;
            } else {
                String searchKey;

                for (Map.Entry<String, List<String>> entry : searchData.entrySet()) {
                    searchKey = entry.getKey();

                    if (this.search.containsKey(searchKey)) {
                        this.search.get(searchKey).addAll(entry.getValue());
                    } else {
                        this.search.put(searchKey, entry.getValue());
                    }
                }
            }
        }

        return this;
    }

    public Url setSearch(String searchKey, String searchValue, int index) {
        if (searchKey != null && !searchKey.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
            List<String> searchValues;

            if (search.containsKey(searchKey)) {
                searchValues = search.get(searchKey);
            } else {
                searchValues = new ArrayList<>();
                search.put(searchKey, searchValues);
            }

            searchValues.set(index, searchValue);
        }

        return this;
    }
    public Url addSearch(String searchKey, String searchValue) {
        return addSearch(searchKey, searchValue, -1);
    }
    public Url addSearch(String searchKey, String searchValue, int index) {
        if (searchKey != null && !searchKey.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
            List<String> searchValues;

            if (search.containsKey(searchKey)) {
                searchValues = search.get(searchKey);
            } else {
                searchValues = new ArrayList<>();
                search.put(searchKey, searchValues);
            }

            if (index < 0) {
                searchValues.add(searchValue);
            } else {
                searchValues.add(index, searchValue);
            }
        }

        return this;
    }

    public Url setSearch(String searchKey, List<String> searchValues) {
        if (searchKey != null) {
            search.put(searchKey, searchValues);
        }

        return this;
    }
    public Url addSearch(String searchKey, List<String> searchValues) {
        if (searchKey != null) {
            if (search.containsKey(searchKey)) {
                search.get(searchKey).addAll(searchValues);
            } else {
                search.put(searchKey, searchValues);
            }
        }

        return this;
    }

    public Url deleteSearch(String searchKey) {
        if (search != null) {
            search.remove(searchKey);
        }

        return this;
    }
    public Url deleteSearch(String searchKey, int index) {
        if (search != null && search.containsKey(searchKey)) {
            search.get(searchKey).remove(index);
        }

        return this;
    }

    public boolean matchSearch(String search) {
        if (search == null && (this.search == null || this.search.isEmpty())) {
            return true;
        } else if (search == null) {
            return false;
        } else if (this.search == null) {
            return false;
        }

        String key;

        for (Map.Entry<String, List<String>> entry : parseSearch(search).entrySet()) {
            key = entry.getKey();

            if (!this.search.containsKey(key)) {
                return false;
            }

            if (!this.search.get(key).containsAll(entry.getValue())) {
                return false;
            }
        }

        return true;
    }

    public String getHash() {
        return hash;
    }
    public Url setHash(String hash) {
        if (hash == null || hash.isEmpty()) {
            this.hash = null;
        } else {
            this.hash = hash;
        }

        return this;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        _host(out);
        _path(out);
        _search(out);
        _hash(out);

        return out.toString();
    }

    public String toString(String... data) {
        StringBuilder out = new StringBuilder();

        int i = 0, length = (int) Math.floor(data.length / 2);

        Map<String, String> replace     = new HashMap<>(length);

        for (; i < length; i++) {
            replace.put(data[i * 2], data[(i * 2) + 1]);
        }

        _host(out);
        _path(out);
        _search(out, replace);
        _hash(out);

        return out.toString();
    }

    private void _host(StringBuilder out) {
        if (host != null) {
            if (protocol != null) {
                out
                        .append(protocol.toLowerCase())
                        .append("://");
            }

            if (userName != null) {
                out.append(userName);

                if (password != null) {
                    out
                            .append(":")
                            .append(password);
                }

                out.append("@");
            }

            if (host != null) {
                out.append(host);
            }

            if (port > 0) {
                out
                        .append(":")
                        .append(port);
            }
        }
    }
    private void _path(StringBuilder out) {
        if (path != null) {
            out
                    .append(path);
        }
    }
    private void _search(StringBuilder out) {
        _search(out, null);
    }
    private void _search(StringBuilder out, Map<String, String> replace) {
        String key;
        List<String> value;
        int i, j;

        if (search != null && search.size() > 0) {
            out.append("?");

            i = search.size();

            for (Map.Entry<String, List<String>> entry : search.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();

                if (replace == null || !replace.containsKey(key)) {
                    if (value.size() == 1) {
                        out
                                .append(key)
                                .append("=")
                                .append(URLEncodeEncode(value.get(0)));
                    } else {
                        j = value.size();

                        for (String _value : value) {
                            out
                                    .append(entry.getKey())
                                    .append("[]=")
                                    .append(URLElementDecode(_value));

                            if (j-- > 1) {
                                out.append("&");
                            }
                        }
                    }

                    if (i-- > 1) {
                        out.append("&");
                    }
                }
            }
        }

        if (replace != null && replace.size() > 0) {
            if (search == null || search.size() == 0)
                out.append("?");

            String lastChar = String.valueOf(out.charAt(out.length()-1));

            if (!"?".equals(lastChar) && !"&".equals(lastChar))
                out.append("&");

            i = replace.size();

            for (Map.Entry<String, String> entry : replace.entrySet()) {
                out
                        .append(entry.getKey())
                        .append("=")
                        .append(URLEncodeEncode(entry.getValue()));

                if (i-- > 1)
                    out.append("&");
            }
        }
    }
    private void _hash(StringBuilder out) {
        if (hash != null) {
            out
                    .append("#")
                    .append(hash);
        }
    }
}
