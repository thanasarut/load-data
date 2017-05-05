package sunseries.travel.request.handler.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;

import java.util.HashMap;

@RestController
public class HotelBaseRateController extends BaseController {

    @RequestMapping(value = "/hotels/{hotelId}/base-rate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put("hotel_id", hotelId);
        requestMessage.setType(EventType.CREATE_HOTEL_BASE_RATE);
        return perform(requestMessage, EventType.CREATE_HOTEL_BASE_RATE_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/base-rate/{baseRateId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable String hotelId, @PathVariable String baseRateId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(BASE_RATE_ID, baseRateId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_HOTEL_BASE_RATE);
        return perform(requestMessage, EventType.DELETE_HOTEL_BASE_RATE_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/base-rate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBaseRateByHotelId(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_BASE_RATE);
        return perform(requestMessage, EventType.GET_HOTEL_BASE_RATE_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/base-rate/{baseRateId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String patch(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String baseRateId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.getEventData().put(BASE_RATE_ID, baseRateId);
        requestMessage.setType(EventType.UPDATE_HOTEL_BASE_RATE);
        return perform(requestMessage, EventType.UPDATE_HOTEL_BASE_RATE_RESPONSE, request);
    }

}
