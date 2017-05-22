package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PromotionContainer implements Serializable {

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("promotion_tags")
    private List<String> promotionTag;

    @SerializedName("promotion_allotment_enabled")
    private Boolean promotionAllotmentEnabled;

    @SerializedName("promotions")
    private List promotionList;

    @SerializedName("allotments_promotional_enabled")
    private Boolean allotmentsPromotionalEnabled;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPromotionTag() {
        return promotionTag;
    }

    public void setPromotionTag(List<String> promotionTag) {
        this.promotionTag = promotionTag;
    }

    public List getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List promotionList) {
        this.promotionList = promotionList;
    }

    public Boolean getAllotmentsPromotionalEnabled() {
        return allotmentsPromotionalEnabled;
    }

    public void setAllotmentsPromotionalEnabled(Boolean allotmentsPromotionalEnabled) {
        this.allotmentsPromotionalEnabled = allotmentsPromotionalEnabled;
    }

    public Boolean getPromotionAllotmentEnabled() {
        return promotionAllotmentEnabled;
    }

    public void setPromotionAllotmentEnabled(Boolean promotionAllotmentEnabled) {
        this.promotionAllotmentEnabled = promotionAllotmentEnabled;
    }

    @Override
    public String toString() {
        return "PromotionContainer{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", promotionTag=" + promotionTag +
                ", allotmentsPromotionalEnabled=" + allotmentsPromotionalEnabled +
                ", promotionList=" + promotionList +
                '}';
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
        return gson.toJson(this);
    }

}
