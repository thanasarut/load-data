package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class HotelMinimumNightStayController extends BaseController {

    @RequestMapping(value = "/hotels/{hotelId}/minimum-night-stay", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put("hotel_id", hotelId);
        requestMessage.setType(EventType.CREATE_HOTEL_MINIMUM_NIGHT_STAY);
        return perform(requestMessage, EventType.CREATE_HOTEL_MINIMUM_NIGHT_STAY_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/minimum-night-stay/{minimumNightStayId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable String hotelId, @PathVariable String minimumNightStayId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(MINIMUM_NIGHT_STAY_ID, minimumNightStayId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_HOTEL_MINIMUM_NIGHT_STAY);
        return perform(requestMessage, EventType.DELETE_HOTEL_MINIMUM_NIGHT_STAY_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/minimum-night-stay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMinimumNightStaysByHotelId(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_MINIMUM_NIGHT_STAY);
        return perform(requestMessage, EventType.GET_HOTEL_MINIMUM_NIGHT_STAY_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/minimum-night-stay/{minimumNightStayId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String patch(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String minimumNightStayId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.getEventData().put(MINIMUM_NIGHT_STAY_ID, minimumNightStayId);
        requestMessage.setType(EventType.UPDATE_HOTEL_MINIMUM_NIGHT_STAY);
        return perform(requestMessage, EventType.UPDATE_HOTEL_MINIMUM_NIGHT_STAY_RESPONSE, request);
    }

}
