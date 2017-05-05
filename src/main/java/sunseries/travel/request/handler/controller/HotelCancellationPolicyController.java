package sunseries.travel.request.handler.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import java.util.HashMap;

@RestController
public class HotelCancellationPolicyController extends BaseController {

    @RequestMapping(value = "/hotels/{hotelId}/cancellation-policies", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put("hotel_id", hotelId);
        requestMessage.setType(EventType.CREATE_HOTEL_CANCELLATION_POLICY);
        return perform(requestMessage, EventType.CREATE_HOTEL_CANCELLATION_POLICY_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/cancellation-policies", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.setType(EventType.UPDATE_HOTEL_CANCELLATION_POLICY);
        return perform(requestMessage, EventType.CREATE_HOTEL_CANCELLATION_POLICY_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/cancellation-policies/{cancellationPolicyId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable String hotelId, @PathVariable String  cancellationPolicyId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(CANCELLATION_POLICY_ID, cancellationPolicyId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_HOTEL_CANCELLATION_POLICY);
        return perform(requestMessage, EventType.DELETE_HOTEL_CANCELLATION_POLICY_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/cancellation-policies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String search(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_CANCELLATION_POLICY);
        return perform(requestMessage, EventType.GET_HOTEL_CANCELLATION_POLICY_RESPONSE, request);
    }

}
