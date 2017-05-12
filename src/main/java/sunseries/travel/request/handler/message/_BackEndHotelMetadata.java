package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by vick.thanasarut on 4/10/2017 AD.
 */
public class _BackEndHotelMetadata implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("supplier_id")
    private String supplierId;
    @SerializedName("name")
    private String name;
    @SerializedName("types")
    private List<Map<String, Object>> types;
    @SerializedName("description")
    private String description;
    @SerializedName("city")
    private Map<String, Object> city;
    @SerializedName("area_id")
    private String areaId;
    @SerializedName("stars")
    private String stars;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("fax")
    private String fax;
    @SerializedName("address")
    private String address;
    @SerializedName("amenities")
    private List<Map<String, Object>> amenities;
    @SerializedName("remarks")
    private Object remarks;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("images")
    private List<_Image> images;
    @SerializedName("room_count")
    private Integer roomCount;
    @SerializedName("service_id")
    private String serviceId;

    @SerializedName("room_classes")
    private List<Map<String, Object>> roomClasses;
    @SerializedName("child_policy")
    private Map<String, Object> childPolicy;
    @SerializedName("reservation_email")
    private String reservationEmail;
    @SerializedName("official_email")
    private String officialEmail;
    @SerializedName("sales_email")
    private String salesEmail;
    @SerializedName("cancellation_policies")
    private List<_CancellationPolicies> cancellationPolicies;
    @SerializedName("facade_id")
    private String facadeId;
    @SerializedName("minimum_night_stay_periods")
    private List<_MinimumNightStay> minimumNightStayPeriods;
    @SerializedName("options")
    private List<_Option> options;

    /*// recheck minimumNightStayPeriod
    @SerializedName("closed_allotments")
    private List<Map<String, Object>> closeAllotments;
    // recheck options
    @SerializedName("rate_provides")
    private List<Map<String, Object>> rateProviders;
    @SerializedName("child_policies")
    private Object childPolicies;
    @SerializedName("accounts_email")
    private String accountEmail;
    @SerializedName("email")
    private String email;
    // recheck allocations
    @SerializedName("allocations")
    private List<Map<String, Object>> allocations;
    @SerializedName("payment_order_criteria")
    private String paymentOrderCriteria;
    // recheck services
    @SerializedName("services")
    private Object services;
    // recheck facilities
    @SerializedName("facilities")
    private Object facilities;
    @SerializedName("cas_version")
    private Integer caseVersion;
    @SerializedName("url")
    private String url;
    @SerializedName("rating")
    private String rating;
    @SerializedName("region_id")
    private String regionId;
    @SerializedName("cancellation_day")
    private Object cancellationDay;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, Object>> getTypes() {
        return types;
    }

    public void setTypes(List<Map<String, Object>> types) {
        this.types = types;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getCity() {
        return city;
    }

    public void setCity(Map<String, Object> city) {
        this.city = city;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Map<String, Object>> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Map<String, Object>> amenities) {
        this.amenities = amenities;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<_Image> getImages() {
        return images;
    }

    public void setImages(List<_Image> images) {
        this.images = images;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<Map<String, Object>> getRoomClasses() {
        return roomClasses;
    }

    public void setRoomClasses(List<Map<String, Object>> roomClasses) {
        this.roomClasses = roomClasses;
    }

    public String getReservationEmail() {
        return reservationEmail;
    }

    public void setReservationEmail(String reservationEmail) {
        this.reservationEmail = reservationEmail;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public String getSalesEmail() {
        return salesEmail;
    }

    public void setSalesEmail(String salesEmail) {
        this.salesEmail = salesEmail;
    }

    public List<_CancellationPolicies> getCancellationPolicies() {
        return cancellationPolicies;
    }

    public void setCancellationPolicies(List<_CancellationPolicies> cancellationPolicies) {
        this.cancellationPolicies = cancellationPolicies;
    }

    public Map<String, Object> getChildPolicy() {
        return childPolicy;
    }

    public void setChildPolicy(Map<String, Object> childPolicy) {
        this.childPolicy = childPolicy;
    }

    public String getFacadeId() {
        return facadeId;
    }

    public void setFacadeId(String facadeId) {
        this.facadeId = facadeId;
    }

    public List<_MinimumNightStay> getMinimumNightStayPeriods() {
        return minimumNightStayPeriods;
    }

    public void setMinimumNightStayPeriods(List<_MinimumNightStay> minimumNightStayPeriods) {
        this.minimumNightStayPeriods = minimumNightStayPeriods;
    }

    public List<_Option> getOptions() {
        return options;
    }

    public void setOptions(List<_Option> options) {
        this.options = options;
    }
}
