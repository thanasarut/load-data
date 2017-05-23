package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BlackoutPeriod implements Serializable {

    @SerializedName("blackout_from")
    private String blackoutFrom;

    @SerializedName("blackout_to")
    private String blackoutTo;

    @SerializedName("room_classes")
    private List<String> roomClass;

    @SerializedName("applicable_days_of_weeks")
    private List<String> applicableDaysOfWeek;

    public String getBlackoutFrom() {
        return blackoutFrom;
    }

    public void setBlackoutFrom(String blackoutFrom) {
        this.blackoutFrom = blackoutFrom;
    }

    public String getBlackoutTo() {
        return blackoutTo;
    }

    public void setBlackoutTo(String blackoutTo) {
        this.blackoutTo = blackoutTo;
    }

    public List<String> getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(List<String> roomClass) {
        this.roomClass = roomClass;
    }


    public List<String> getApplicableDaysOfWeek() {
        return applicableDaysOfWeek;
    }

    public void setApplicableDaysOfWeek(List<String> applicableDaysOfWeek) {
        this.applicableDaysOfWeek = applicableDaysOfWeek;
    }

}
