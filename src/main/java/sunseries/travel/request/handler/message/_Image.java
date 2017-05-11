package sunseries.travel.request.handler.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class _Image {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("image_url")
    private Object imageUrl;

    @Expose
    @SerializedName("cloudinary_name")
    private String cloudinaryName;

    @Expose
    @SerializedName("thumbnail_url")
    private Object thumbnailUrl;

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

    public String getCloudinaryName() {
        return cloudinaryName;
    }

    public void setCloudinaryName(String cloudinaryName) {
        this.cloudinaryName = cloudinaryName;
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

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(Object thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
