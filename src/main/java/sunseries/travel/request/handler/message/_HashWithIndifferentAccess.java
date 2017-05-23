package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by thanasarut on 5/19/2017 AD.
 */
public class _HashWithIndifferentAccess {

    @SerializedName("^o")
    private String o;

    @SerializedName("self")
    private Map<String, Object> self;

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public Map<String, Object> getSelf() {
        return self;
    }

    public void setSelf(Map<String, Object> self) {
        this.self = self;
    }
}
