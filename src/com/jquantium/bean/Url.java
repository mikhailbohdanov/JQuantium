package com.jquantium.bean;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static final Pattern urlPattern = Pattern.compile("^([A-z:]+:)?(?:\\/\\/)?(?:([\\w\\-.@]*)(?::(.*))?@)?([\\w\\-.]+)?(?::([0-9]+))?(?:(\\/)([^?#\\s\\/]+)?|([^?#\\s]*\\/)([^?#\\s\\/]*))?(?:\\?([^#]*))?(?:#(.*))?$");

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

        Matcher matcher = urlPattern.matcher(urlString);

        context
                .setProtocol(matcher)
                .setUserInfo(matcher)
                .setDomain(matcher)
                .setPort(matcher)
                .setPath(matcher)
                .setFile(matcher)
                .setSearch(matcher)
                .setHash(matcher);

        return context;
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
    private String domain;
    private int port;
    private String path;
    private String file;
    private Map<String, List<String>> search;
    private String hash;

    public Url() {}
    public Url(String url) {
        parse(url, this);
    }
    public Url(HttpServletRequest request) {
        setPath(request.getRequestURI().replaceAll("/$", ""));

        if (path.isEmpty()) {
            path = "/";
        }

        List<String> values;
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            values = new ArrayList<>();

            for (String value : entry.getValue()) {
                values.add(value);
            }

            setSearch(entry.getKey(), values);
        }
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
    public Url setProtocol(Matcher matcher) {
        String tmp;

        if (matcher != null && matcher.matches() && (tmp = matcher.group(1)) != null) {
            setProtocol(tmp.substring(0, tmp.length() - 1));
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
    public Url setUserName(Matcher matcher) {
        if (matcher != null && matcher.matches()) {
            setUserName(matcher.group(2));
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
    public Url setPassword(Matcher matcher) {
        if (matcher != null && matcher.matches()) {
            setPassword(matcher.group(3));
        }

        return this;
    }

    public String getUserInfo() {
        if (userName == null && password == null) {
            return null;
        } else if (password == null) {
            return userName;
        } else {
            StringBuilder userInfo = new StringBuilder();

            userInfo
                    .append(userName)
                    .append(":")
                    .append(password);

            return userInfo.toString();
        }
    }
    public Url setUserInfo(String userInfo) {
        String[] vars;

        if (userInfo != null && !userInfo.isEmpty()) {
            vars    = userInfo.split(":");

            if (vars.length > 1) {
                this.userName   = vars[0];
                this.password   = vars[1];
            } else
                this.userName   = vars[0];
        }

        return this;
    }
    public Url setUserInfo(Matcher matcher) {
        setUserName(matcher);
        setPassword(matcher);

        return this;
    }

    public String getDomain() {
        return domain;
    }
    public Url setDomain(String domain) {
        if (domain == null || domain.isEmpty()) {
            this.domain = null;
        } else {
            this.domain = domain;
        }

        return this;
    }
    public Url setDomain(Matcher matcher) {
        if (matcher != null && matcher.matches()) {
            setDomain(matcher.group(4));
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
    public Url setPort(String port) {
        if (port != null && !port.isEmpty()) {
            setPort(Integer.parseInt(port));
        }

        return this;
    }
    public Url setPort(Matcher matcher) {
        if (matcher != null && matcher.matches()) {
            setPort(matcher.group(5));
        }

        return this;
    }

    public String getPath() {
        return path;
    }
    public String getPathFull() {
        return path + "/" + file;
    }
    public Url setPath(String path) {
        if (path == null || path.isEmpty()) {
            this.path = null;
        } else {
            this.path = path;
        }

        return this;
    }
    public Url setPath(Matcher matcher) {
        if (matcher != null && matcher.matches()) {
            String tmp = matcher.group(6);

            if (tmp != null) {
                setPath(tmp);
            } else {
                setPath(matcher.group(8));
            }
        }

        return this;
    }
    public Url setPathFull(String path) {
        Matcher matcher = urlPattern.matcher(path);

        setPath(matcher);
        setFile(matcher);

        return this;
    }

    public String getFile() {
        return file;
    }
    public Url setFile(String file) {
        if (file == null || file.isEmpty()) {
            this.file = null;
        } else {
            this.file = file;
        }

        return this;
    }
    public Url setFile(Matcher matcher) {
        if (matcher != null && matcher.matches()) {
            String tmp = matcher.group(7);

            if (tmp != null) {
                setFile(tmp);
            } else {
                setFile(matcher.group(9));
            }
        }

        return this;
    }

    public Map<String, List<String>> getSearch() {
        return search;
    }
    public List<String> getSearch(String searchKey) {
        if (searchKey == null || search == null) {
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

    public Url setSearch(Matcher matcher) {
        if (matcher != null && matcher.matches()) {
            setSearch(matcher.group(10));
        }

        return this;
    }
    public Url addSearch(Matcher matcher) {
        if (matcher != null && matcher.matches()) {
            addSearch(matcher.group(8));
        }

        return this;
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
            return this;
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

            if (search == null) {
                search = new TreeMap<>();
            }

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

            if (search == null) {
                search = new TreeMap<>();
            }

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
            if (search == null) {
                search = new TreeMap<>();
            }

            search.put(searchKey, searchValues);
        }

        return this;
    }
    public Url addSearch(String searchKey, List<String> searchValues) {
        if (searchKey != null) {
            if (search == null) {
                search = new TreeMap<>();
            }

            if (search.containsKey(searchKey)) {
                search.get(searchKey).addAll(searchValues);
            } else {
                search.put(searchKey, searchValues);
            }
        }

        return this;
    }

    public Url deleteSearch(String searchKey) {
        if (searchKey != null && search != null) {
            search.remove(searchKey);
        }

        return this;
    }
    public Url deleteSearch(String searchKey, int index) {
        if (searchKey != null && search != null && search.containsKey(searchKey)) {
            search.get(searchKey).remove(index);
        }

        return this;
    }

    public boolean matchSearch(String search) {
        if ((search == null || search.isEmpty()) && (this.search == null || this.search.isEmpty())) {
            return true;
        } else if (search == null || search.isEmpty() || this.search == null || this.search.isEmpty()) {
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
    public Url setHash(Matcher matcher) {
        if (matcher != null && matcher.matches()) {
            setHash(matcher.group(11));
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
        if (domain != null) {
            if (protocol != null) {
                out
                        .append(protocol)
                        .append("://");
            }

            if (userName != null) {
                out
                        .append(getUserInfo())
                        .append("@");
            }

            out.append(domain);

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
                    .append(path)
                    .append(file);
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
            if (search == null || search.size() == 0) {
                out.append("?");
            }

            String lastChar = String.valueOf(out.charAt(out.length() - 1));

            if (!"?".equals(lastChar) && !"&".equals(lastChar)) {
                out.append("&");
            }

            i = replace.size();

            for (Map.Entry<String, String> entry : replace.entrySet()) {
                out
                        .append(entry.getKey())
                        .append("=")
                        .append(URLEncodeEncode(entry.getValue()));

                if (i-- > 1) {
                    out.append("&");
                }
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
