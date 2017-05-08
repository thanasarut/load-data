package sunseries.travel.request.handler.message;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelMinimumNightStay extends BaseDocument {

    @SerializedName("hotel_id")
    private String hotelId;

    @SerializedName("minimum_night_stays")
    private List<MiniMumNightStay> miniMumNightStayList;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public List<MiniMumNightStay> getMiniMumNightStayList() {
        return miniMumNightStayList;
    }

    public void setMiniMumNightStayList(List<MiniMumNightStay> miniMumNightStayList) {
        this.miniMumNightStayList = miniMumNightStayList;
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
        return gson.toJson(this);
    }

}
