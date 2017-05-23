package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class FreeUpgradePromotion extends Promotion {

    @SerializedName("upgrade_to")
    private Map<String, String> upgradeTo;

    @SerializedName("is_guarantee")
    private Boolean isGuarantee;

    public Map<String, String> getUpgradeTo() {
        return upgradeTo;
    }

    public void setUpgradeTo(Map<String, String> upgradeTo) {
        this.upgradeTo = upgradeTo;
    }

    public Boolean getGuarantee() {
        return isGuarantee;
    }

    public void setGuarantee(Boolean guarantee) {
        isGuarantee = guarantee;
    }

    @Override
    public String toString() {
        return "FreeUpgradePromotion{" +
                "upgradeTo=" + upgradeTo +
                ", isGuarantee=" + isGuarantee +
                '}';
    }

}

