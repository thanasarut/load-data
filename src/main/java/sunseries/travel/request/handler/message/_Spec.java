package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;
import org.apache.camel.language.SpEL;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class _Spec implements Serializable {

    @SerializedName("^o")
    private String o;

    @SerializedName("origin")
    private String origin;

    @SerializedName("id")
    private String id;

    @SerializedName("code")
    private String code;

    @SerializedName("internal_code")
    private String internalCode;

    @SerializedName("hotel_service_id")
    private String hotelServiceId;

    @SerializedName("name")
    private String name;

    @SerializedName("applicable_period")
    private _PeriodB applicablePeriod;

    @SerializedName("applicable_days")
    private List<String> applicableDays;

    @SerializedName("is_early_bird")
    private Boolean isEarlyBird;

    @SerializedName("earlybird_days_in_advance")
    private Integer earlybirdDaysInAdvance;

    @SerializedName("type")
    private String type;

    @SerializedName("market")
    private String market;

    @SerializedName("room_classes")
    private List<String> roomClasses;

    @SerializedName("combinable")
    private Boolean combinable;

    @SerializedName("exclusive")
    private Boolean exclusive;

    @SerializedName("inflexible")
    private Boolean inflexible;

    @SerializedName("forced_combinable")
    private Boolean forcedCombinable;

    @SerializedName("description")
    private String description;

    @SerializedName("book_by")
    private Object bookByDate;

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

    @SerializedName("qualifying_nights")
    private Integer qualifyingNight;

    @SerializedName("total_qualifying_nights")
    private Integer totalQualifyingNights;

    @SerializedName("total_maximum_night_stay")
    private Integer totalMaximumNightStay;

    @SerializedName("args")
    private _HashWithIndifferentAccess args;

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

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

    public String getHotelServiceId() {
        return hotelServiceId;
    }

    public void setHotelServiceId(String hotelServiceId) {
        this.hotelServiceId = hotelServiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public _PeriodB getApplicablePeriod() {
        return applicablePeriod;
    }

    public void setApplicablePeriod(_PeriodB applicablePeriod) {
        this.applicablePeriod = applicablePeriod;
    }

    public List<String> getApplicableDays() {
        return applicableDays;
    }

    public void setApplicableDays(List<String> applicableDays) {
        this.applicableDays = applicableDays;
    }

    public Boolean getEarlyBird() {
        return isEarlyBird;
    }

    public void setEarlyBird(Boolean earlyBird) {
        isEarlyBird = earlyBird;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public List<String> getRoomClasses() {
        return roomClasses;
    }

    public void setRoomClasses(List<String> roomClasses) {
        this.roomClasses = roomClasses;
    }

    public Boolean getCombinable() {
        return combinable;
    }

    public void setCombinable(Boolean combinable) {
        this.combinable = combinable;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

    public Boolean getInflexible() {
        return inflexible;
    }

    public void setInflexible(Boolean inflexible) {
        this.inflexible = inflexible;
    }

    public Boolean getForcedCombinable() {
        return forcedCombinable;
    }

    public void setForcedCombinable(Boolean forcedCombinable) {
        this.forcedCombinable = forcedCombinable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getBookByDate() {
        return bookByDate;
    }

    public void setBookByDate(Object bookByDate) {
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

    public Integer getQualifyingNight() {
        return qualifyingNight;
    }

    public void setQualifyingNight(Integer qualifyingNight) {
        this.qualifyingNight = qualifyingNight;
    }

    public Integer getTotalQualifyingNights() {
        return totalQualifyingNights;
    }

    public void setTotalQualifyingNights(Integer totalQualifyingNights) {
        this.totalQualifyingNights = totalQualifyingNights;
    }

    public Integer getTotalMaximumNightStay() {
        return totalMaximumNightStay;
    }

    public void setTotalMaximumNightStay(Integer totalMaximumNightStay) {
        this.totalMaximumNightStay = totalMaximumNightStay;
    }

    public _HashWithIndifferentAccess getArgs() {
        return args;
    }

    public void setArgs(_HashWithIndifferentAccess args) {
        this.args = args;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public Integer getEarlybirdDaysInAdvance() {
        return earlybirdDaysInAdvance;
    }

    public void setEarlybirdDaysInAdvance(Integer earlybirdDaysInAdvance) {
        this.earlybirdDaysInAdvance = earlybirdDaysInAdvance;
    }
}
