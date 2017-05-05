package sunseries.travel.request.handler.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.services.TokenService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.HashMap;

@RestController
public class HotelController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value="/hotels", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createHotel(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.CREATE_HOTEL);
        return perform(requestMessage, EventType.CREATE_HOTEL_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHotels(HttpServletRequest request)
            throws InterruptedException {
        EventNotification requestMessage = createEventNotification();
        requestMessage.setType(EventType.GET_ALL_HOTEL);
        return perform(requestMessage, EventType.GET_ALL_HOTEL_RESPONSE, request);
    }

    @RequestMapping(value="/hotels/{hotelId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String patchHotel(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.setType(EventType.UPDATE_HOTEL);
        return perform(requestMessage, EventType.UPDATE_HOTEL_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteHotel(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_HOTEL);
        return perform(requestMessage, EventType.DELETE_HOTEL_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHotel(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL);
        return perform(requestMessage, EventType.GET_HOTEL_RESPONSE, request);
    }

    @RequestMapping(value="/hotels/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String search(@RequestBody EventNotification requestMessage, HttpServletRequest request, @RequestParam String token)
            throws InterruptedException {

        Gson gson = new GsonBuilder().create();
        JsonObject eventData = gson.fromJson(gson.toJson(requestMessage.getEventData()), JsonObject.class);
        JsonObject searchCriteria = eventData.get("search_criteria").getAsJsonObject();

        searchCriteria.addProperty(AGENT_ID, tokenService.findAccessToken(token).getAgentId());
        eventData.add("search_criteria", searchCriteria);

        Type collectionType = new TypeToken<HashMap<String, Object>>(){}.getType();
        HashMap<String, Object> newEventData =  new GsonBuilder().create().fromJson(eventData, collectionType);
        requestMessage.setEventData(newEventData);

        requestMessage.setType(EventType.HOTEL_SEARCH_ALL);
        return perform(requestMessage, EventType.HOTEL_SEARCH_ALL_RESPONSE, request);

    }

    @RequestMapping(value="/hotels/search/{city}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchByCity(@RequestBody EventNotification requestMessage,
                               @RequestParam String token,
                               @PathVariable String city,
                               HttpServletRequest request)
            throws InterruptedException {

        Gson gson = new GsonBuilder().create();
        JsonObject eventData = gson.fromJson(gson.toJson(requestMessage.getEventData()), JsonObject.class);
        JsonObject searchCriteria = eventData.get("search_criteria").getAsJsonObject();
        JsonObject criteria = searchCriteria.get("criteria").getAsJsonObject();

        criteria.addProperty("city", city);
        searchCriteria.add("criteria", criteria);
        searchCriteria.addProperty(AGENT_ID, tokenService.findAccessToken(token).getAgentId());
        eventData.add("search_criteria", searchCriteria);

        Type collectionType = new TypeToken<HashMap<String, Object>>(){}.getType();
        HashMap<String, Object> newEventData =  new GsonBuilder().create().fromJson(eventData, collectionType);
        requestMessage.setEventData(newEventData);

        requestMessage.setType(EventType.HOTEL_SEARCH_BY_CITY);
        return perform(requestMessage, EventType.HOTEL_SEARCH_ALL_RESPONSE, request);

    }

}
