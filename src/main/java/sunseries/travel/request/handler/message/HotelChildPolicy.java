package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelChildPolicy extends BaseDocument {

    @SerializedName("hotel_id")
    private String hotelId;

    @SerializedName("child_policies")
    private List<ChildPolicy> childPolicyList;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public List<ChildPolicy> getChildPolicyList() {
        return childPolicyList;
    }

    public void setChildPolicyList(List<ChildPolicy> childPolicyList) {
        this.childPolicyList = childPolicyList;
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
        return gson.toJson(this);
    }

}