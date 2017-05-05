package sunseries.travel.request.handler.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BedType {

    @Expose
    @SerializedName("bed_type_name")
    private String bedTypeName;

    @Expose
    @SerializedName("is_extra_bed")
    private Boolean isExtraBed;

    public BedType(String bedTypeName, Boolean isExtraBed) {
        this.bedTypeName = bedTypeName;
        this.isExtraBed = isExtraBed;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public Boolean getExtraBed() {
        return isExtraBed;
    }

    public void setExtraBed(Boolean extraBed) {
        isExtraBed = extraBed;
    }

}
