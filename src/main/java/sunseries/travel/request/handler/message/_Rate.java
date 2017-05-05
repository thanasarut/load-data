package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by thanasarut on 5/2/2017 AD.
 */
public class _Rate {

    @SerializedName("^o")
    private String _o;

    @SerializedName("id")
    private String id;

    @SerializedName("amount")
    private _Money amount;

    /*@SerializedName()
    private List<_Money> net_costs;*/

    @SerializedName("sale_markup")
    private _Markup saleMarkup;

    @SerializedName("display_markup")
    private _Markup displayMarkup;

    @SerializedName("tax")
    private Map<String, String> tax;

    @SerializedName("extras")
    private List<String> extras;

    @SerializedName("valid_days")
    private List<String> validDay;

    @SerializedName("valid_period")
    //private _Period valid_period;
    private Map<String, Object> valid_period;

    public String get_o() {
        return _o;
    }

    public void set_o(String _o) {
        this._o = _o;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public _Money getAmount() {
        return amount;
    }

    public void setAmount(_Money amount) {
        this.amount = amount;
    }

    public _Markup getSaleMarkup() {
        return saleMarkup;
    }

    public void setSaleMarkup(_Markup saleMarkup) {
        this.saleMarkup = saleMarkup;
    }

    public _Markup getDisplayMarkup() {
        return displayMarkup;
    }

    public void setDisplayMarkup(_Markup displayMarkup) {
        this.displayMarkup = displayMarkup;
    }

    public Map<String, String> getTax() {
        return tax;
    }

    public void setTax(Map<String, String> tax) {
        this.tax = tax;
    }

    public List<String> getExtras() {
        return extras;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }

    public List<String> getValidDay() {
        return validDay;
    }

    public void setValidDay(List<String> validDay) {
        this.validDay = validDay;
    }

    public Map<String, Object> getValid_period() {
        return valid_period;
    }

    public void setValid_period(Map<String, Object> valid_period) {
        this.valid_period = valid_period;
    }
}
