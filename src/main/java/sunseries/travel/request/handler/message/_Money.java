package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thanasarut on 5/2/2017 AD.
 */
public class _Money {

    @SerializedName("cents")
    private Long satang;

    @SerializedName("currency")
    private String currency;

    public Long getSatang() {
        return satang;
    }

    public void setSatang(Long satang) {
        this.satang = satang;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
