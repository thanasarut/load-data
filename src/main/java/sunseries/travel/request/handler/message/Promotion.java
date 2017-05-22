package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Promotion implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("code")
    private String code;

    @SerializedName("type")
    private String type;

    @SerializedName("is_force_combine")
    private Boolean isForceCombine;

    @SerializedName("is_exclusive_combine")
    private Boolean isExclusiveCombine;

    @SerializedName("is_inflexible")
    private Boolean isInflexible;

    @SerializedName("applicable_from")
    private String applicableFrom;

    @SerializedName("applicable_to")
    private String applicableTo;

    @SerializedName("market")
    private String market;

    @SerializedName("early_birds_days")
    private Integer earlyBirdsDays;

    @SerializedName("book_by_date")
    private String bookByDate;

    @SerializedName("minimum_night_stay_inside_promotion")
    private Integer minimumNightStayInsidePromotion;

    @SerializedName("minimum_rooms")
    private Integer minimumRooms;

    @SerializedName("maximum_night_stay")
    private Integer maximumNightStay;

    @SerializedName("minimum_night_stay")
    private Integer minimumNightStay;

    @SerializedName("maximum_applied_nights")
    private Integer maximumAppliedNights;

    @SerializedName("applicable_days_of_weeks")
    private List<String> applicableDaysOfWeek;

    @SerializedName("applicable_room_classes")
    private List<String> applicableRoomClass;

    @SerializedName("promotion_charges")
    private List<PromotionCharge> promotionCharges;

    @SerializedName("blackout_periods")
    private List<BlackoutPeriod> blackoutPeriods;

    @SerializedName("currency_code")
    private String currencyCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getForceCombine() {
        return isForceCombine;
    }

    public void setForceCombine(Boolean forceCombine) {
        isForceCombine = forceCombine;
    }

    public Boolean getExclusiveCombine() {
        return isExclusiveCombine;
    }

    public void setExclusiveCombine(Boolean exclusiveCombine) {
        isExclusiveCombine = exclusiveCombine;
    }

    public Boolean getInflexible() {
        return isInflexible;
    }

    public void setInflexible(Boolean inflexible) {
        isInflexible = inflexible;
    }

    public String getApplicableFrom() {
        return applicableFrom;
    }

    public void setApplicableFrom(String applicableFrom) {
        this.applicableFrom = applicableFrom;
    }

    public String getApplicableTo() {
        return applicableTo;
    }

    public void setApplicableTo(String applicableTo) {
        this.applicableTo = applicableTo;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Integer getEarlyBirdsDays() {
        return earlyBirdsDays;
    }

    public void setEarlyBirdsDays(Integer earlyBirdsDays) {
        this.earlyBirdsDays = earlyBirdsDays;
    }

    public String getBookByDate() {
        return bookByDate;
    }

    public void setBookByDate(String bookByDate) {
        this.bookByDate = bookByDate;
    }

    public Integer getMinimumNightStayInsidePromotion() {
        return minimumNightStayInsidePromotion;
    }

    public void setMinimumNightStayInsidePromotion(Integer minimumNightStayInsidePromotion) {
        this.minimumNightStayInsidePromotion = minimumNightStayInsidePromotion;
    }

    public Integer getMinimumRooms() {
        return minimumRooms;
    }

    public void setMinimumRooms(Integer minimumRooms) {
        this.minimumRooms = minimumRooms;
    }

    public Integer getMaximumNightStay() {
        return maximumNightStay;
    }

    public void setMaximumNightStay(Integer maximumNightStay) {
        this.maximumNightStay = maximumNightStay;
    }

    public Integer getMinimumNightStay() {
        return minimumNightStay;
    }

    public void setMinimumNightStay(Integer minimumNightStay) {
        this.minimumNightStay = minimumNightStay;
    }

    public Integer getMaximumAppliedNights() {
        return maximumAppliedNights;
    }

    public void setMaximumAppliedNights(Integer maximumAppliedNights) {
        this.maximumAppliedNights = maximumAppliedNights;
    }

    public List<String> getApplicableDaysOfWeek() {
        return applicableDaysOfWeek;
    }

    public void setApplicableDaysOfWeek(List<String> applicableDaysOfWeek) {
        this.applicableDaysOfWeek = applicableDaysOfWeek;
    }

    public List<String> getApplicableRoomClass() {
        return applicableRoomClass;
    }

    public void setApplicableRoomClass(List<String> applicableRoomClass) {
        this.applicableRoomClass = applicableRoomClass;
    }

    public List<PromotionCharge> getPromotionCharges() {
        return promotionCharges;
    }

    public void setPromotionCharges(List<PromotionCharge> promotionCharges) {
        this.promotionCharges = promotionCharges;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public List<BlackoutPeriod> getBlackoutPeriods() {
        return blackoutPeriods;
    }

    public void setBlackoutPeriods(List<BlackoutPeriod> blackoutPeriods) {
        this.blackoutPeriods = blackoutPeriods;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", isForceCombine=" + isForceCombine +
                ", isExclusiveCombine=" + isExclusiveCombine +
                ", isInflexible=" + isInflexible +
                ", applicableFrom='" + applicableFrom + '\'' +
                ", applicableTo='" + applicableTo + '\'' +
                ", market='" + market + '\'' +
                ", earlyBirdsDays=" + earlyBirdsDays +
                ", bookByDate='" + bookByDate + '\'' +
                ", minimumNightStayInsidePromotion=" + minimumNightStayInsidePromotion +
                ", minimumRooms=" + minimumRooms +
                ", maximumNightStay=" + maximumNightStay +
                ", minimumNightStay=" + minimumNightStay +
                ", maximumAppliedNights=" + maximumAppliedNights +
                ", applicableDaysOfWeek=" + applicableDaysOfWeek +
                ", applicableRoomClass=" + applicableRoomClass +
                ", promotionCharges=" + promotionCharges +
                ", blackoutPeriods=" + blackoutPeriods +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
