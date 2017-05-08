package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.Map;


public class _MinimumNightStay {

    @SerializedName("^o")
    private String o;

    @SerializedName("id")
    private String id;

    @SerializedName("minimum_night_stay")
    private Object minimumNightStay;

    @SerializedName("period")
    private Map<String, Object> period;

    @SerializedName("application_criteria")
    private String applicationCriteria;

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

    public Object getMinimumNightStay() {
        return minimumNightStay;
    }

    public void setMinimumNightStay(Object minimumNightStay) {
        this.minimumNightStay = minimumNightStay;
    }

    public Map<String, Object> getPeriod() {
        return period;
    }

    public void setPeriod(Map<String, Object> period) {
        this.period = period;
    }

    public String getApplicationCriteria() {
        return applicationCriteria;
    }

    public void setApplicationCriteria(String applicationCriteria) {
        this.applicationCriteria = applicationCriteria;
    }

    /*@Override
    public String toString() {
        return "CancellationPolicy{" +
                "id='" + id + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", roomClass='" + roomClass + '\'' +
                ", days=" + days +
                '}';
    }*/
}
