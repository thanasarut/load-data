package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BaseRate {

    @SerializedName("id")
    private String id;

    @SerializedName("market")
    private String market;

    @SerializedName("from_date")
    private String fromDate;

    @SerializedName("to_date")
    private String toDate;

    @SerializedName("room_class")
    private String roomClass;

    @SerializedName("room_rate")
    private String roomRate;

    @SerializedName("breakfast_rate")
    private String breakfastRate;

    @SerializedName("extra_bed_rate")
    private String extraBedRate;

    @SerializedName("currency_code")
    private String currencyCode;

    @SerializedName("sale_markup")
    private SaleMarkup saleMarkup;

    @SerializedName("weekdays")
    private List<String> weekdays;

    @SerializedName("params")
    private List<Param> params;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
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

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public String getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(String roomRate) {
        this.roomRate = roomRate;
    }

    public String getBreakfastRate() {
        return breakfastRate;
    }

    public void setBreakfastRate(String breakfastRate) {
        this.breakfastRate = breakfastRate;
    }

    public String getExtraBedRate() {
        return extraBedRate;
    }

    public void setExtraBedRate(String extraBedRate) {
        this.extraBedRate = extraBedRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public SaleMarkup getSaleMarkup() {
        return saleMarkup;
    }

    public void setSaleMarkup(SaleMarkup saleMarkup) {
        this.saleMarkup = saleMarkup;
    }

    public List<String> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<String> weekdays) {
        this.weekdays = weekdays;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseRate{" +
                "id='" + id + '\'' +
                ", market='" + market + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", roomClass='" + roomClass + '\'' +
                ", roomRate='" + roomRate + '\'' +
                ", breakfastRate='" + breakfastRate + '\'' +
                ", extraBedRate='" + extraBedRate + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", saleMarkup=" + saleMarkup +
                ", weekdays=" + weekdays +
                ", params=" + params +
                '}';
    }

}
