package sunseries.travel.request.handler.message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class EventNotification implements Serializable {

    private static Logger log = LoggerFactory.getLogger(EventNotification.class.getName());

    @SerializedName("id")
    private String id;              //unique ex."1234"

    @SerializedName("type")
    private String type;            //ex. "new_log"

    @SerializedName("ttid")
    private String ttid;            //ex. "1234567890"

    @SerializedName("origin")
    private String origin;          //ex. "sunseries_gem"

    @SerializedName("datetime")
    private Date datetime;          //ex. "yyyy-MM-dd'T'HH:mm:ssZ"

    @SerializedName("event_data")
    private HashMap<String, Object> eventData = new HashMap<>();

    @SerializedName("timezone")
    private Date timezone;

    @SerializedName("processing_time")
    private long processingTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTtid() {
        return ttid;
    }

    public void setTtid(String ttid) {
        this.ttid = ttid;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public HashMap<String, Object> getEventData() {
        return eventData;
    }

    public void setEventData(HashMap<String, Object> eventData) {
        this.eventData = eventData;
    }

    public long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create();
        String jsonString = gson.toJson(this);
        log.debug(jsonString);
        return jsonString;
    }

}
