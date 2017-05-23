package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by thanasarut on 5/23/2017 AD.
 */
public class _RoomClass {

    @SerializedName("^o")
    private String o;

    @SerializedName("rooms")
    private List<Map<String, Object>> rooms;

    @SerializedName("room_configurations")
    private List<Map<String, Object>> roomConfigurations;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("max_occupancy_without_extra_bed")
    private Object maxOccupancyWithoutExtraBed;

    @SerializedName("max_occupancy_with_extra_bed")
    private Object maxOccupancyWithExtrabed;

    @SerializedName("max_adults_with_extra_bed")
    private Object maxAdultsWithExtraBed;

    @SerializedName("facilities")
    private Object facilities;

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public List<Map<String, Object>> getRooms() {
        return rooms;
    }

    public void setRooms(List<Map<String, Object>> rooms) {
        this.rooms = rooms;
    }

    public List<Map<String, Object>> getRoomConfigurations() {
        return roomConfigurations;
    }

    public void setRoomConfigurations(List<Map<String, Object>> roomConfigurations) {
        this.roomConfigurations = roomConfigurations;
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

    public Object getMaxOccupancyWithoutExtraBed() {
        return maxOccupancyWithoutExtraBed;
    }

    public void setMaxOccupancyWithoutExtraBed(Object maxOccupancyWithoutExtraBed) {
        this.maxOccupancyWithoutExtraBed = maxOccupancyWithoutExtraBed;
    }

    public Object getMaxOccupancyWithExtrabed() {
        return maxOccupancyWithExtrabed;
    }

    public void setMaxOccupancyWithExtrabed(Object maxOccupancyWithExtrabed) {
        this.maxOccupancyWithExtrabed = maxOccupancyWithExtrabed;
    }

    public Object getMaxAdultsWithExtraBed() {
        return maxAdultsWithExtraBed;
    }

    public void setMaxAdultsWithExtraBed(Object maxAdultsWithExtraBed) {
        this.maxAdultsWithExtraBed = maxAdultsWithExtraBed;
    }

    public Object getFacilities() {
        return facilities;
    }

    public void setFacilities(Object facilities) {
        this.facilities = facilities;
    }
}
