package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thanasarut on 5/2/2017 AD.
 */
public class _Markup {

    @SerializedName("^o")
    private String name;

    @SerializedName("markup")
    private _Money markup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public _Money getMarkup() {
        return markup;
    }

    public void setMarkup(_Money markup) {
        this.markup = markup;
    }
}
