package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by thanasarut on 5/2/2017 AD.
 */
public class _Market {

    @SerializedName("market")
    private String market;

    @SerializedName(":room")
    private List<_Rate> room;

    @SerializedName(":extra_bed")
    private List<_Rate> extraBed;

    @SerializedName(":break_fast")
    private List<_Rate> breakFast;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public List<_Rate> getRoom() {
        return room;
    }

    public void setRoom(List<_Rate> room) {
        this.room = room;
    }

    public List<_Rate> getExtraBed() {
        return extraBed;
    }

    public void setExtraBed(List<_Rate> extraBed) {
        this.extraBed = extraBed;
    }

    public List<_Rate> getBreakFast() {
        return breakFast;
    }

    public void setBreakFast(List<_Rate> breakFast) {
        this.breakFast = breakFast;
    }
}
