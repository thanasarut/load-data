package sunseries.travel.request.handler.message;

import com.google.gson.annotations.SerializedName;

public class AccessToken {

    @SerializedName("email")
    private String email;

    @SerializedName("agent_id")
    private String agentId;

    @SerializedName("user_type")
    private String userType;

    @SerializedName("business_title")
    private String businessTitle;

    @SerializedName("token")
    private String token;

    @SerializedName("expired")
    private String expired;

    public AccessToken() {
        super();
    }

    public AccessToken(String email, String token, String expired) {
        this.email = email;
        this.token = token;
        this.expired = expired;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getBusinessTitle() {
        return businessTitle;
    }

    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle;
    }

}
