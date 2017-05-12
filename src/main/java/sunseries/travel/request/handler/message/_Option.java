package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by pea.chiwa on 11/1/16.
 */
public class _Option {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("rate")
    private _Rate rate;

    @SerializedName("applies_to")
    private String appliesTo;

    @SerializedName("applies_every")
    private String appliesEvery;

    @SerializedName("compulsory")
    private boolean compulsory;

    @SerializedName("period")
    private Map<String, Object> period;

    @SerializedName("market")
    private String market;

    @SerializedName("room_class")
    private String roomClass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(String appliesTo) {
        this.appliesTo = appliesTo;
    }

    public String getAppliesEvery() {
        return appliesEvery;
    }

    public void setAppliesEvery(String appliesEvery) {
        this.appliesEvery = appliesEvery;
    }

    public boolean isCompulsory() {
        return compulsory;
    }

    public void setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public _Rate getRate() {
        return rate;
    }

    public void setRate(_Rate rate) {
        this.rate = rate;
    }

    public Map<String, Object> getPeriod() {
        return period;
    }

    public void setPeriod(Map<String, Object> period) {
        this.period = period;
    }

    /*@Override
    public String toString() {
        return "Option{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rate='" + String.format("%1$.2f", rate.getAmount().getSatang() / 100) + '\'' +
                ", currencyCode='" + rate.getAmount().getCurrency() + '\'' +
                ", appliesTo='" + appliesTo + '\'' +
                ", appliesEvery='" + appliesEvery + '\'' +
                ", compulsory=" + compulsory +
                ", fromDate='" + period.getFrom().toString() + '\'' +
                ", toDate='" + period.getTo().toString() + '\'' +
                ", market='" + market + '\'' +
                ", roomClass='" + roomClass + '\'' +
                '}';
    }*/
}
