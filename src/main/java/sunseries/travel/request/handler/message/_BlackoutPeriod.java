package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thanasarut on 5/19/2017 AD.
 */
public class _BlackoutPeriod {
    @SerializedName("period")
    private _PeriodB period;

    @SerializedName("room_class_ids")
    private List<String> roomClassIds;

    @SerializedName("id")
    private String id;

    public _PeriodB getPeriod() {
        return period;
    }

    public void setPeriod(_PeriodB period) {
        this.period = period;
    }

    public List<String> getRoomClassIds() {
        return roomClassIds;
    }

    public void setRoomClassIds(List<String> roomClassIds) {
        this.roomClassIds = roomClassIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

