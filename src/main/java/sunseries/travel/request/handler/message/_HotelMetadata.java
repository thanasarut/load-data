package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by vick.thanasarut on 4/10/2017 AD.
 */
public class _HotelMetadata implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("types")
    private List<Map<String, Object>> types;
    @SerializedName("description")
    private String description;
    @SerializedName("city_id")
    private String cityId;
    @SerializedName("area_id")
    private String areaId;
    @SerializedName("stars")
    private String stars;
    @SerializedName("telephone")
    private String telephone;
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
    @SerializedName("fax")
    private String fax;
    @SerializedName("opening_date")
    private String openingDate;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getStars() {
        return stars;
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

    public Object getRemarks() {
        return remarks;
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

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public List<Map<String, Object>> getTypes() {
        return types;
    }

    public void setTypes(List<Map<String, Object>> types) {
        this.types = types;
    }

    public List<Map<String, Object>> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Map<String, Object>> amenities) {
        this.amenities = amenities;
    }

    public List<_Image> getImages() {
        return images;
    }

    public void setImages(List<_Image> images) {
        this.images = images;
    }

    public void setRemarks(List<Map<String, Object>> remarks) {
        this.remarks = remarks;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }
}
