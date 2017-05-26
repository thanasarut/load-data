package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thanasarut on 5/24/2017 AD.
 */
public class _PromotionCharge {

    @SerializedName("name")
    private String name;

    @SerializedName("amount")
    private _Money amount;

    @SerializedName("markup")
    private String markup;

    @SerializedName("required")
    private String required;

    @SerializedName("application_criteria")
    private String applicationCriteria;

    @SerializedName("applies_to")
    private String appliesTo;

    @SerializedName("applies_every")
    private String appliesEvery;

    @SerializedName("room_classes")
    private List<String> roomClasses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public _Money getAmount() {
        return amount;
    }

    public void setAmount(_Money amount) {
        this.amount = amount;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getApplicationCriteria() {
        return applicationCriteria;
    }

    public void setApplicationCriteria(String applicationCriteria) {
        this.applicationCriteria = applicationCriteria;
    }

    public String getAppliesEvery() {
        return appliesEvery;
    }

    public void setAppliesEvery(String appliesEvery) {
        this.appliesEvery = appliesEvery;
    }

    public List<String> getRoomClasses() {
        return roomClasses;
    }

    public void setRoomClasses(List<String> roomClasses) {
        this.roomClasses = roomClasses;
    }

    public String getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(String appliesTo) {
        this.appliesTo = appliesTo;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }
}
