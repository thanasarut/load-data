package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;


public class CancellationPolicy {

    @SerializedName("id")
    private String id;

    @SerializedName("from_date")
    private String fromDate;

    @SerializedName("to_date")
    private String toDate;

    @SerializedName("room_class")
    private String roomClass;

    @SerializedName("days")
    private int days;

    @SerializedName("market")
    private String market;

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

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "CancellationPolicy{" +
                "id='" + id + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", roomClass='" + roomClass + '\'' +
                ", days=" + days +
                '}';
    }
}
