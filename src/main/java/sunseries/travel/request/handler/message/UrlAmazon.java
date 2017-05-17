package sunseries.travel.request.handler.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UrlAmazon {

    @Expose
    @SerializedName("scheme")
    private String scheme;

    @Expose
    @SerializedName("user")
    private String user;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("host")
    private String host;

    @Expose
    @SerializedName("port")
    private Integer port;

    @Expose
    @SerializedName("path")
    private String path;

    @Expose
    @SerializedName("query")
    private String query;

    @Expose
    @SerializedName("opaque")
    private String opaque;

    @Expose
    @SerializedName("fragment")
    private String fragment;

    @Expose
    @SerializedName("parser")
    private String parser;

    @Expose
    @SerializedName("registry")
    private String registry;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOpaque() {
        return opaque;
    }

    public void setOpaque(String opaque) {
        this.opaque = opaque;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public String getParser() {
        return parser;
    }

    public void setParser(String parser) {
        this.parser = parser;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String toString() {
        return this.scheme + "://" + this.host + this.path;
    }
}
