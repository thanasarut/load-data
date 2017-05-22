package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class FreeProductPromotion extends Promotion {

    @SerializedName("free_product_specifications")
    private List<String> freeProductSpecifications;

    public List<String> getFreeProductSpecifications() {
        return freeProductSpecifications;
    }

    public void setFreeProductSpecifications(List<String> freeProductSpecifications) {
        this.freeProductSpecifications = freeProductSpecifications;
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
        return gson.toJson(this);
    }

}
