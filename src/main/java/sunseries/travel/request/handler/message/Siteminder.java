package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Siteminder {

    @SerializedName("rate_plan_name")
    private String ratePlanName;

    @SerializedName("markup_type")
    private String markupType;

    @SerializedName("markup_amount")
    private Integer markupAmount;

    @SerializedName("incentive_amount")
    private Integer incentiveAmount;

    @SerializedName("includes_breakfast")
    private Boolean includesBreakfast;

    @SerializedName("cancellation_days")
    private Integer cancellationDays;

    @SerializedName("deduct_commission")
    private Boolean deductCommission;

    @SerializedName("commission_applies_to_extra_bed")
    private Boolean commissionAppliesToExtraBed;

    @SerializedName("share_stop_sale")
    private Boolean shareStopSale;

    @SerializedName("share_allotments")
    private Boolean shareAllotments;

    @SerializedName("allotment_only")
    private Boolean allotmentOnly;

    @SerializedName("rate_protection")
    private Integer rateProtection;

    @SerializedName("hotel_options")
    private List<String> hotelOptions;

    @SerializedName("earlybird_days")
    private Integer earlybirdDays;

    @SerializedName("applicable_promotions")
    private List<String> applicablePromotions;

    public String getRatePlanName() {
        return ratePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

    public String getMarkupType() {
        return markupType;
    }

    public void setMarkupType(String markupType) {
        this.markupType = markupType;
    }

    public Integer getMarkupAmount() {
        return markupAmount;
    }

    public void setMarkupAmount(Integer markupAmount) {
        this.markupAmount = markupAmount;
    }

    public Integer getIncentiveAmount() {
        return incentiveAmount;
    }

    public void setIncentiveAmount(Integer incentiveAmount) {
        this.incentiveAmount = incentiveAmount;
    }

    public Boolean getIncludesBreakfast() {
        return includesBreakfast;
    }

    public void setIncludesBreakfast(Boolean includesBreakfast) {
        this.includesBreakfast = includesBreakfast;
    }

    public Integer getCancellationDays() {
        return cancellationDays;
    }

    public void setCancellationDays(Integer cancellationDays) {
        this.cancellationDays = cancellationDays;
    }

    public Boolean getDeductCommission() {
        return deductCommission;
    }

    public void setDeductCommission(Boolean deductCommission) {
        this.deductCommission = deductCommission;
    }

    public Boolean getCommissionAppliesToExtraBed() {
        return commissionAppliesToExtraBed;
    }

    public void setCommissionAppliesToExtraBed(Boolean commissionAppliesToExtraBed) {
        this.commissionAppliesToExtraBed = commissionAppliesToExtraBed;
    }

    public Boolean getShareStopSale() {
        return shareStopSale;
    }

    public void setShareStopSale(Boolean shareStopSale) {
        this.shareStopSale = shareStopSale;
    }

    public Boolean getShareAllotments() {
        return shareAllotments;
    }

    public void setShareAllotments(Boolean shareAllotments) {
        this.shareAllotments = shareAllotments;
    }

    public Boolean getAllotmentOnly() {
        return allotmentOnly;
    }

    public void setAllotmentOnly(Boolean allotmentOnly) {
        this.allotmentOnly = allotmentOnly;
    }

    public Integer getRateProtection() {
        return rateProtection;
    }

    public void setRateProtection(Integer rateProtection) {
        this.rateProtection = rateProtection;
    }

    public List<String> getHotelOptions() {
        return hotelOptions;
    }

    public void setHotelOptions(List<String> hotelOptions) {
        this.hotelOptions = hotelOptions;
    }

    public Integer getEarlybirdDays() {
        return earlybirdDays;
    }

    public void setEarlybirdDays(Integer earlybirdDays) {
        this.earlybirdDays = earlybirdDays;
    }

    public List<String> getApplicablePromotions() {
        return applicablePromotions;
    }

    public void setApplicablePromotions(List<String> applicablePromotions) {
        this.applicablePromotions = applicablePromotions;
    }

}