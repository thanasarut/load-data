package sunseries.travel.request.handler.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ImageAmazon {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("image_url")
    private UrlAmazon imageUrl;

    @Expose
    @SerializedName("thumbnail_url")
    private UrlAmazon thumbnailUrl;

    @Expose
    @SerializedName("is_primary")
    private Boolean isPrimary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UrlAmazon getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(UrlAmazon imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UrlAmazon getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(UrlAmazon thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
