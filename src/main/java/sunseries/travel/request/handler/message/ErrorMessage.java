package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

public class ErrorMessage {

    @SerializedName("code")
    private String code;

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public ErrorMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
