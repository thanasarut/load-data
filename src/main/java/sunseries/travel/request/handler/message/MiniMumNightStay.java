
package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;


public class MiniMumNightStay {

    @SerializedName("id")
    private String id;

    @SerializedName("application_criteria")
    private String applicationCriteria;

    @SerializedName("room_class")
    private String roomClass;

    @SerializedName("nights")
    private int nights;

    @SerializedName("from_date")
    private String fromDate;

    @SerializedName("to_date")
    private String toDate;

    @SerializedName("market")
    private String market;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationCriteria() {
        return applicationCriteria;
    }

    public void setApplicationCriteria(String applicationCriteria) {
        this.applicationCriteria = applicationCriteria;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
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

    @Override
    public String toString() {
        return "MiniMumNightStay{" +
                "id='" + id + '\'' +
                ", applicationCriteria='" + applicationCriteria + '\'' +
                ", roomClass='" + roomClass + '\'' +
                ", nights=" + nights +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", market='" + market + '\'' +
                '}';
    }

}
