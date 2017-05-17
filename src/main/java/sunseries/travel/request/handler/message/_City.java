package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thanasarut on 5/16/2017 AD.
 */
public class _City {

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private String id;

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("country_id")
    private String countryId;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("areas")
    private List<_Area> areaList;

    @SerializedName("image")
    private _Image image;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<_Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<_Area> areaList) {
        this.areaList = areaList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public _Image getImage() {
        return image;
    }

    public void setImage(_Image image) {
        this.image = image;
    }
}
