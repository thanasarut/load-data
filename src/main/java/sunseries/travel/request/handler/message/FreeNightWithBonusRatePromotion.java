package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FreeNightWithBonusRatePromotion extends Promotion {

    @SerializedName("paid_nights")
    private Integer paidNights;

    @SerializedName("free_nights")
    private Integer freeNights;

    @SerializedName("promotion_extension_rate_specific_options")
    private List<FreeNightWithBonusExtensionRateSpecification> freeNightWithBonusExtensionRateSpecification;

    @SerializedName("promotion_free_rate_specific_options")
    private List<FreeNightWithBonusFreeRateSpecification> freeNightWithBonusFreeRateSpecification;

    @SerializedName("extension_rate_applied_all_nights_end_of_booking")
    private Boolean extensionRateAppliedAllNightsEndOfBooking;

    public List<FreeNightWithBonusExtensionRateSpecification> getFreeNightWithBonusExtensionRateSpecification() {
        return freeNightWithBonusExtensionRateSpecification;
    }

    public void setFreeNightWithBonusExtensionRateSpecification(List<FreeNightWithBonusExtensionRateSpecification> freeNightWithBonusExtensionRateSpecification) {
        this.freeNightWithBonusExtensionRateSpecification = freeNightWithBonusExtensionRateSpecification;
    }

    public Integer getPaidNights() {
        return paidNights;
    }

    public void setPaidNights(Integer paidNights) {
        this.paidNights = paidNights;
    }

    public Integer getFreeNights() {
        return freeNights;
    }

    public void setFreeNights(Integer freeNights) {
        this.freeNights = freeNights;
    }

    public List<FreeNightWithBonusFreeRateSpecification> getFreeNightWithBonusFreeRateSpecification() {
        return freeNightWithBonusFreeRateSpecification;
    }

    public void setFreeNightWithBonusFreeRateSpecification(List<FreeNightWithBonusFreeRateSpecification> freeNightWithBonusFreeRateSpecification) {
        this.freeNightWithBonusFreeRateSpecification = freeNightWithBonusFreeRateSpecification;
    }

    public Boolean getExtensionRateAppliedAllNightsEndOfBooking() {
        return extensionRateAppliedAllNightsEndOfBooking;
    }

    public void setExtensionRateAppliedAllNightsEndOfBooking(Boolean extensionRateAppliedAllNightsEndOfBooking) {
        this.extensionRateAppliedAllNightsEndOfBooking = extensionRateAppliedAllNightsEndOfBooking;
    }

}

