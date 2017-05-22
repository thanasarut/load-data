package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

public class PercentageDiscountPromotion extends Promotion {

    @SerializedName("percentage_discount")
    private Integer percentageDiscount;

    @SerializedName("is_extra_bed_applicable")
    private Boolean isExtraBedApplicable;

    @SerializedName("discount_extra_bed")
    private Boolean discountExtraBed;

    public Integer getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Integer percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public Boolean getExtraBedApplicable() {
        return isExtraBedApplicable;
    }

    public void setExtraBedApplicable(Boolean extraBedApplicable) {
        isExtraBedApplicable = extraBedApplicable;
    }

    public Boolean getDiscountExtraBed() {
        return discountExtraBed;
    }

    public void setDiscountExtraBed(Boolean discountExtraBed) {
        this.discountExtraBed = discountExtraBed;
    }

}
