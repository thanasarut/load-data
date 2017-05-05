package sunseries.travel.request.handler.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RoomClass implements Serializable {

    @Expose
    @SerializedName("room_class_id")
    private String roomClassId;

    @Expose
    @SerializedName("room_class_name")
    private String roomClassName;

    @Expose
    @SerializedName("order")
    private Integer order;

    @Expose
    @SerializedName("bed_types")
    private List<BedType> bedTypes;

    @Expose
    @SerializedName("max_occupancy_exclude_extra_bed")
    private String maxOccupancyExcludeExtraBed;

    @Expose
    @SerializedName("max_occupancy_include_extra_bed")
    private String maxOccupancyIncludeExtraBed;

    @Expose
    @SerializedName("max_adult_include_extra_bed")
    private String maxAdultIncludeExtraBed;

    @Expose
    @SerializedName("is_mix_adult_and_child_in_room")
    private Boolean isMixAdultAndChildInRoom;

    @Expose
    @SerializedName("max_child")
    private String maxChild;

    public String getRoomClassId() {
        return roomClassId;
    }

    public void setRoomClassId(String roomClassId) {
        this.roomClassId = roomClassId;
    }

    public String getRoomClassName() {
        return roomClassName;
    }

    public void setRoomClassName(String roomClassName) {
        this.roomClassName = roomClassName;
    }

    public List<BedType> getBedTypes() {
        return bedTypes;
    }

    public void setBedTypes(List<BedType> bedTypes) {
        this.bedTypes = bedTypes;
    }

    public String getMaxOccupancyExcludeExtraBed() {
        return maxOccupancyExcludeExtraBed;
    }

    public void setMaxOccupancyExcludeExtraBed(String maxOccupancyExcludeExtraBed) {
        this.maxOccupancyExcludeExtraBed = maxOccupancyExcludeExtraBed;
    }

    public String getMaxOccupancyIncludeExtraBed() {
        return maxOccupancyIncludeExtraBed;
    }

    public void setMaxOccupancyIncludeExtraBed(String maxOccupancyIncludeExtraBed) {
        this.maxOccupancyIncludeExtraBed = maxOccupancyIncludeExtraBed;
    }

    public String getMaxAdultIncludeExtraBed() {
        return maxAdultIncludeExtraBed;
    }

    public void setMaxAdultIncludeExtraBed(String maxAdultIncludeExtraBed) {
        this.maxAdultIncludeExtraBed = maxAdultIncludeExtraBed;
    }

    public Boolean getMixAdultAndChildInRoom() {
        return isMixAdultAndChildInRoom;
    }

    public void setMixAdultAndChildInRoom(Boolean mixAdultAndChildInRoom) {
        isMixAdultAndChildInRoom = mixAdultAndChildInRoom;
    }

    public String getMaxChild() {
        return maxChild;
    }

    public void setMaxChild(String maxChild) {
        this.maxChild = maxChild;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

}
