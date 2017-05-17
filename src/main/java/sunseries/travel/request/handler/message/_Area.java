package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thanasarut on 5/16/2017 AD.
 */
public class _Area {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

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
}
