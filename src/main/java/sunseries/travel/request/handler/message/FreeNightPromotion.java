package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class FreeNightPromotion extends Promotion {

    @SerializedName("paid_nights")
    private Integer paidNights;

    @SerializedName("free_nights")
    private Integer freeNights;

    @SerializedName("free_night_rate_specifications")
    private List<FreeNightRateSpecification> freeNightRateSpecifications;

    public FreeNightPromotion() {
        super();
    }

    public Integer getPaidNights() {
        return paidNights;
    }

    public void setPaidNights(Integer paidNights) {
        this.paidNights = paidNights;
    }

    public Integer getFreeNights() {
        return freeNights;
    }

    public void setFreeNights(Integer freeNights) {
        this.freeNights = freeNights;
    }

    public List<FreeNightRateSpecification> getFreeNightRateSpecifications() {
        return freeNightRateSpecifications;
    }

    public void setFreeNightRateSpecifications(List<FreeNightRateSpecification> freeNightRateSpecifications) {
        this.freeNightRateSpecifications = freeNightRateSpecifications;
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
        return gson.toJson(this);
    }

}

