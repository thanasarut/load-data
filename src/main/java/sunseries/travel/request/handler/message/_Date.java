package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;


public class _Date {

    @SerializedName("year")
    private Float year;

    @SerializedName("month")
    private Float month;

    @SerializedName("day")
    private Float day;

    @SerializedName("start")
    private Float start;

    public Float getYear() {
        return year;
    }

    public void setYear(Float year) {
        this.year = year;
    }

    public Float getMonth() {
        return month;
    }

    public void setMonth(Float month) {
        this.month = month;
    }

    public Float getDay() {
        return day;
    }

    public void setDay(Float day) {
        this.day = day;
    }

    public Float getStart() {
        return start;
    }

    public void setStart(Float start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return String.format("%1$d", (int)Float.parseFloat(year.toString())) + "-" + String.format("%1$02d", (int) Float.parseFloat(month.toString())) + "-" + String.format("%1$02d", (int)Float.parseFloat(day.toString()));
    }
}
