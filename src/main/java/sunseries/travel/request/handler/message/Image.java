package sunseries.travel.request.handler.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Image {

    @Expose
    @SerializedName("image_url")
    private String imageUrl;

    @Expose
    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @Expose
    @SerializedName("is_primary")
    private Boolean isPrimary;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
