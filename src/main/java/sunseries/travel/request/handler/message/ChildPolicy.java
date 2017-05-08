
package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;


public class ChildPolicy {

    @SerializedName("id")
    private String id;

    @SerializedName("from_date")
    private String fromDate;

    @SerializedName("to_date")
    private String toDate;

    @SerializedName("currency_code")
    private String currencyCode;

    @SerializedName("breakfast_rate")
    private String breakfastRate;

    @SerializedName("breakfast_is_compulsory")
    private Boolean breakfastIsCompulsory;

    @SerializedName("extra_bed_rate")
    private String extraBedRate;

    @SerializedName("extra_bed_is_compulsory")
    private Boolean extraBedIsCompulsory;

    @SerializedName("extra_bed_includes_child_breakfast")
    private Boolean extraBedIncludesChildBreakfast;

    @SerializedName("charge_first_child")
    private Boolean chargeFirstChild;

    @SerializedName("room_class")
    private String roomClass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getBreakfastRate() {
        return breakfastRate;
    }

    public void setBreakfastRate(String breakfastRate) {
        this.breakfastRate = breakfastRate;
    }

    public Boolean getBreakfastIsCompulsory() {
        return breakfastIsCompulsory;
    }

    public void setBreakfastIsCompulsory(Boolean breakfastIsCompulsory) {
        this.breakfastIsCompulsory = breakfastIsCompulsory;
    }

    public String getExtraBedRate() {
        return extraBedRate;
    }

    public void setExtraBedRate(String extraBedRate) {
        this.extraBedRate = extraBedRate;
    }

    public Boolean getExtraBedIsCompulsory() {
        return extraBedIsCompulsory;
    }

    public void setExtraBedIsCompulsory(Boolean extraBedIsCompulsory) {
        this.extraBedIsCompulsory = extraBedIsCompulsory;
    }

    public Boolean getExtraBedIncludesChildBreakfast() {
        return extraBedIncludesChildBreakfast;
    }

    public void setExtraBedIncludesChildBreakfast(Boolean extraBedIncludesChildBreakfast) {
        this.extraBedIncludesChildBreakfast = extraBedIncludesChildBreakfast;
    }

    public Boolean getChargeFirstChild() {
        return chargeFirstChild;
    }

    public void setChargeFirstChild(Boolean chargeFirstChild) {
        this.chargeFirstChild = chargeFirstChild;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    @Override
    public String toString() {
        return "ChildPolicy{" +
                "id='" + id + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", breakfastRate='" + breakfastRate + '\'' +
                ", breakfastIsCompulsory=" + breakfastIsCompulsory +
                ", extraBedRate='" + extraBedRate + '\'' +
                ", extraBedIsCompulsory=" + extraBedIsCompulsory +
                ", extraBedIncludesChildBreakfast=" + extraBedIncludesChildBreakfast +
                ", chargeFirstChild=" + chargeFirstChild +
                ", roomClass='" + roomClass + '\'' +
                '}';
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
        return gson.toJson(this);
    }

}
