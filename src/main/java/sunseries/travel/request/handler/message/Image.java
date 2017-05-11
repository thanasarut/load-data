package sunseries.travel.request.handler.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Image {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("image_url")
    private String imageUrl;

    @Expose
    @SerializedName("cloudinary_name")
    private String cloudinaryName;

    @Expose
    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @Expose
    @SerializedName("public_id")
    private String publicId;

    @Expose
    @SerializedName("is_primary")
    private Boolean isPrimary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCloudinaryName() {
        return cloudinaryName;
    }

    public void setCloudinaryName(String cloudinaryName) {
        this.cloudinaryName = cloudinaryName;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
