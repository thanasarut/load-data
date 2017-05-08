
package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HotelBaseRate extends BaseDocument {

    @SerializedName("hotel_id")
    private String hotelId;

    @SerializedName("base_rate")
    private List<BaseRate> baseRateList;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }


    public List<BaseRate> getBaseRateList() {
        return baseRateList;
    }

    public void setBaseRateList(List<BaseRate> baseRateList) {
        this.baseRateList = baseRateList;
    }

    @Override
    public String toString() {
        return "HotelBaseRate{" +
                "hotelId='" + hotelId + '\'' +
                ", baseRateList=" + baseRateList +
                '}';
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
        return gson.toJson(this);
    }

}
