package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

public class FlatRateSpecification {

    @SerializedName("room_class")
    private String roomClass;

    @SerializedName("sunday")
    private String sunday;

    @SerializedName("monday")
    private String monday;

    @SerializedName("tuesday")
    private String tuesday;

    @SerializedName("wednesday")
    private String wednesday;

    @SerializedName("thursday")
    private String thursday;

    @SerializedName("friday")
    private String friday;

    @SerializedName("saturday")
    private String saturday;

    @SerializedName("extra_bed")
    private String extraBed;

    @SerializedName("breakfast")
    private String breakfast;

    @SerializedName("breakfast_applicability")
    private String breakfastApplicability;

    @SerializedName("markup")
    private String markup;

    public FlatRateSpecification(String roomClass, String sunday, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String extraBed, String breakfast, String breakfastApplicability, String markup) {
        this.roomClass = roomClass;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.extraBed = extraBed;
        this.breakfast = breakfast;
        this.breakfastApplicability = breakfastApplicability;
        this.markup = markup;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getExtraBed() {
        return extraBed;
    }

    public void setExtraBed(String extraBed) {
        this.extraBed = extraBed;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getBreakfastApplicability() {
        return breakfastApplicability;
    }

    public void setBreakfastApplicability(String breakfastApplicability) {
        this.breakfastApplicability = breakfastApplicability;
    }

    public String getMarkup() {
        return markup;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

}
