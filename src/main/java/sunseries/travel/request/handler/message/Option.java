package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pea.chiwa on 11/1/16.
 */
public class Option {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("rate")
    private String rate;

    @SerializedName("currency_code")
    private String currencyCode;

    @SerializedName("applies_to")
    private String appliesTo;

    @SerializedName("applies_every")
    private String appliesEvery;

    @SerializedName("compulsory")
    private boolean compulsory;

    @SerializedName("from_date")
    private String fromDate;

    @SerializedName("to_date")
    private String toDate;

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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
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

    @Override
    public String toString() {
        return "Option{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rate='" + rate + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", appliesTo='" + appliesTo + '\'' +
                ", appliesEvery='" + appliesEvery + '\'' +
                ", compulsory=" + compulsory +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", market='" + market + '\'' +
                ", roomClass='" + roomClass + '\'' +
                '}';
    }
}
