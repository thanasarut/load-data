package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class _Promotion implements Serializable {

    @SerializedName("^o")
    private String o;

    @SerializedName("id")
    private String id;

    @SerializedName("actions")
    private List actions;

    @SerializedName("checks")
    private List checks;

    @SerializedName("blackout_periods")
    private List<_BlackoutPeriod> blackoutPeriod;

    @SerializedName("spec")
    private Map<String, Object> spec;

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getActions() {
        return actions;
    }

    public void setActions(List actions) {
        this.actions = actions;
    }

    public List getChecks() {
        return checks;
    }

    public void setChecks(List checks) {
        this.checks = checks;
    }

    public List<_BlackoutPeriod> getBlackoutPeriod() {
        return blackoutPeriod;
    }

    public void setBlackoutPeriod(List<_BlackoutPeriod> blackoutPeriod) {
        this.blackoutPeriod = blackoutPeriod;
    }

    public Map<String, Object> getSpec() {
        return spec;
    }

    public void setSpec(Map<String, Object> spec) {
        this.spec = spec;
    }
}
