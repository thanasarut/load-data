package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by thanasarut on 5/18/2017 AD.
 */
public class _PromotionContainer {
    @SerializedName("^o")
    private String o;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("hotel_service_id")
    private String hotelServiceId;

    /*@SerializedName("promotions")
    private List<Map<String, Object>> promotions;*/
    @SerializedName("promotions")
    private List<_Promotion> promotions;

    @SerializedName("description")
    private String description;

    @SerializedName("tags")
    private List<String> tags;

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotelServiceId() {
        return hotelServiceId;
    }

    public void setHotelServiceId(String hotelServiceId) {
        this.hotelServiceId = hotelServiceId;
    }

    /*public List getPromotions() {
        return promotions;
    }

    public void setPromotions(List promotions) {
        this.promotions = promotions;
    }*/

    public List<_Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<_Promotion> promotions) {
        this.promotions = promotions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
