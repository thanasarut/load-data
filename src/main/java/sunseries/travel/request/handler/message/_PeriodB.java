package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thanasarut on 5/18/2017 AD.
 */
public class _PeriodB {

    @SerializedName("^o")
    private String o;

    @SerializedName("from")
    private Object from;

    @SerializedName("to")
    private Object to;

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public Object getTo() {
        return to;
    }

    public void setTo(Object to) {
        this.to = to;
    }
}
