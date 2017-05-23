package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class FlatRatePromotion extends Promotion {

    @SerializedName("flat_rate_specifications")
    private List<FlatRateSpecification> flatRateSpecifications;

    public List<FlatRateSpecification> getFlatRateSpecifications() {
        return flatRateSpecifications;
    }

    public void setFlatRateSpecifications(List<FlatRateSpecification> flatRateSpecifications) {
        this.flatRateSpecifications = flatRateSpecifications;
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
        return gson.toJson(this);
    }
}

