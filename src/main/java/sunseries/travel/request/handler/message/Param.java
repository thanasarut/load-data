package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

public class Param {

    @SerializedName("extra_bed_includes_breakfast")
    private Boolean extraBedIncludeBreakfast;

    public Boolean getExtraBedIncludeBreakfast() {
        return extraBedIncludeBreakfast;
    }

    public void setExtraBedIncludeBreakfast(Boolean extraBedIncludeBreakfast) {
        this.extraBedIncludeBreakfast = extraBedIncludeBreakfast;
    }

}
