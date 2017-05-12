package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Hotel extends BaseDocument {

    @Expose
    @SerializedName("type")
    private String type = "hotel";

    @Expose
    @SerializedName("hotel_id")
    private String hotelId;

    @Expose
    @SerializedName("hotel_name")
    private String hotelName;

    @Expose
    @SerializedName("hotel_types")
    private List<String> hotelTypes;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("city")
    private String city;

    @Expose
    @SerializedName("areas")
    private List<String> areas;

    @Expose
    @SerializedName("star")
    private String star;

    @Expose
    @SerializedName("telephone")
    private String telephone;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("amenities")
    private List<String> amenities;

    @Expose
    @SerializedName("remarks")
    private List<String> remark;

    @Expose
    @SerializedName("location")
    private Location location;

    @Expose
    @SerializedName("room_classes")
    private List<RoomClass> roomClasses = new CopyOnWriteArrayList<>();

    @Expose
    @SerializedName("is_using_actual_stay_for_cancellation_policy")
    private boolean isUsingActualStayForCancellationPolicy = true;

    @Expose
    @SerializedName("children_age_to")
    private Integer childrenAgeTo;

    @Expose
    @SerializedName("children_age_from")
    private Integer childrenAgeFrom;

    @Expose(serialize = false)
    @SerializedName("room_class_sequence_number")
    private Long roomClassSequenceNumber;

    @Expose
    @SerializedName("reservation_email")
    private String reservationEmail;

    @Expose
    @SerializedName("official_email")
    private String officialEmail;

    @Expose
    @SerializedName("allotment_email")
    private String allotmentEmail;

    @Expose
    @SerializedName("sales_email")
    private String salesEmail;

    @Expose
    @SerializedName("connecting_room")
    private TreeMap<String,TreeMap<String,Boolean>> connectingRoomMap =new TreeMap<>();

    @Expose
    @SerializedName("payee_id")
    private String payee;

    @Expose
    @SerializedName("images")
    private List<Image> images;

    @Expose
    @SerializedName("images_amazon")
    private List<ImageAmazon> imagesAmazon;

    @Expose
    @SerializedName("fax")
    private String fax;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<String> getHotelTypes() {
        return hotelTypes;
    }

    public void setHotelTypes(List<String> hotelTypes) {
        this.hotelTypes = hotelTypes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public List<String> getRemark() {
        return remark;
    }

    public void setRemark(List<String> remark) {
        this.remark = remark;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<RoomClass> getRoomClasses() {
        return roomClasses;
    }

    public void setRoomClasses(List<RoomClass> roomClasses) {
        this.roomClasses = roomClasses;
    }

    public boolean isUsingActualStayForCancellationPolicy() {
        return isUsingActualStayForCancellationPolicy;
    }

    public void setUsingActualStayForCancellationPolicy(boolean usingActualStayForCancellationPolicy) {
        isUsingActualStayForCancellationPolicy = usingActualStayForCancellationPolicy;
    }

    public Integer getChildrenAgeTo() {
        return childrenAgeTo;
    }

    public void setChildrenAgeTo(Integer childrenAgeTo) {
        this.childrenAgeTo = childrenAgeTo;
    }

    public Integer getChildrenAgeFrom() {
        return childrenAgeFrom;
    }

    public void setChildrenAgeFrom(Integer childrenAgeFrom) {
        this.childrenAgeFrom = childrenAgeFrom;
    }

    public Long getRoomClassSequenceNumber() {
        return roomClassSequenceNumber;
    }

    public void setRoomClassSequenceNumber(Long roomClassSequenceNumber) {
        this.roomClassSequenceNumber = roomClassSequenceNumber;
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

    public String getAllotmentEmail() {
        return allotmentEmail;
    }

    public void setAllotmentEmail(String allotmentEmail) {
        this.allotmentEmail = allotmentEmail;
    }

    public String getSalesEmail() {
        return salesEmail;
    }

    public void setSalesEmail(String salesEmail) {
        this.salesEmail = salesEmail;
    }


    public TreeMap<String, TreeMap<String, Boolean>> getConnectingRoomMap() {
        return connectingRoomMap;
    }

    public void setConnectingRoomMap(TreeMap<String, TreeMap<String, Boolean>> connectingRoomMap) {
        this.connectingRoomMap = connectingRoomMap;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<ImageAmazon> getImagesAmazon() {
        return imagesAmazon;
    }

    public void setImagesAmazon(List<ImageAmazon> imagesAmazon) {
        this.imagesAmazon = imagesAmazon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
        return gson.toJson(this);
    }

}