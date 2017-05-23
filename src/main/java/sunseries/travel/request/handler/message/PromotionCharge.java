package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PromotionCharge implements Serializable {

    @SerializedName("type")
    private String type;

    @SerializedName("name")
    private String name;

    @SerializedName("amount")
    private String amount;

    @SerializedName("markup")
    private String markup;

    @SerializedName("requirement")
    private String requirement;

    @SerializedName("applies_to")
    private String appliesTo;

    @SerializedName("applies_every")
    private String appliesEvery;

    @SerializedName("room_classes")
    private List<String> roomClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(String appliesTo) {
        this.appliesTo = appliesTo;
    }

    public String getAppliesEvery() {
        return appliesEvery;
    }

    public void setAppliesEvery(String appliesEvery) {
        this.appliesEvery = appliesEvery;
    }

    public List<String> getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(List<String> roomClass) {
        this.roomClass = roomClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
