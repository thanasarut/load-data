package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;


public class _Period {

    @SerializedName("from")
    private _Date from;

    @SerializedName("to")
    private _Date to;

    public _Date getFrom() {
        return from;
    }

    public void setFrom(_Date from) {
        this.from = from;
    }

    public _Date getTo() {
        return to;
    }

    public void setTo(_Date to) {
        this.to = to;
    }

    /*@Override
    public String toString() {
        return "CancellationPolicy{" +
                "id='" + id + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", roomClass='" + roomClass + '\'' +
                ", days=" + days +
                '}';
    }*/
}
