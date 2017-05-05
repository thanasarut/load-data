
package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HotelsCancellationPolicy extends BaseDocument{

    @SerializedName("hotel_id")
    private String hotelId;


    @SerializedName("cancellation_policy")
    private List<CancellationPolicy> cancellationPolicyList;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }


    public List<CancellationPolicy> getCancellationPolicyList() {
        return cancellationPolicyList;
    }

    public void setCancellationPolicyList(List<CancellationPolicy> cancellationPolicyList) {
        this.cancellationPolicyList = cancellationPolicyList;
    }


    @Override
    public String toString() {
        return "HotelsBaseRate{" +
                "hotelId='" + hotelId + '\'' +
                ", cancellationPolicyList=" + cancellationPolicyList +
                '}';
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
        return gson.toJson(this);
    }
}
