package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class HotelOptionController extends BaseController {

    @RequestMapping(value = "/hotels/{hotelId}/option", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.setType(EventType.CREATE_HOTEL_OPTION);
        return perform(requestMessage, EventType.CREATE_HOTEL_OPTION_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/option/{optionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable String hotelId, @PathVariable String optionId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(OPTION_ID, optionId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_HOTEL_OPTION);
        return perform(requestMessage, EventType.DELETE_HOTEL_OPTION_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/option", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOptionByHotelId(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_OPTION);
        return perform(requestMessage, EventType.GET_HOTEL_OPTION_RESPONSE, request);
    }

}
