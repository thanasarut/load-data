package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.Map;


public class _CancellationPolicies {

    @SerializedName("id")
    private String id;

    @SerializedName("days")
    private Float days;

    @SerializedName("period")
    private Map<String, Object> period;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getDay(){
        return days;
    }

    public void setDay(Float days) {
        this.days = days;
    }

    public Float getDays() {
        return days;
    }

    public void setDays(Float days) {
        this.days = days;
    }

    public Map<String, Object> getPeriod() {
        return period;
    }

    public void setPeriod(Map<String, Object> period) {
        this.period = period;
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
