package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

public class FreeNightWithBonusFreeRateSpecification {

    @SerializedName("room_class")
    private String roomClass;

    @SerializedName("breakfast")
    private String breakfast;

    @SerializedName("breakfast_applicability")
    private String breakfastApplicability;

    @SerializedName("is_extra_bed_free")
    private Boolean isExtraBedFree;

    @SerializedName("is_extra_bed_include_breakfast")
    private Boolean isExtraBedIncludeBreakfast;

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getBreakfastApplicability() {
        return breakfastApplicability;
    }

    public void setBreakfastApplicability(String breakfastApplicability) {
        this.breakfastApplicability = breakfastApplicability;
    }

    public Boolean getExtraBedFree() {
        return isExtraBedFree;
    }

    public void setExtraBedFree(Boolean extraBedFree) {
        isExtraBedFree = extraBedFree;
    }

    public Boolean getExtraBedIncludeBreakfast() {
        return isExtraBedIncludeBreakfast;
    }

    public void setExtraBedIncludeBreakfast(Boolean extraBedIncludeBreakfast) {
        isExtraBedIncludeBreakfast = extraBedIncludeBreakfast;
    }

}
