package sunseries.travel.request.handler.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class HotelChildPolicyController extends BaseController {

    @RequestMapping(value = "/hotels/{hotelId}/child-policy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put("hotel_id", hotelId);
        requestMessage.setType(EventType.CREATE_HOTEL_CHILD_POLICY);
        return perform(requestMessage, EventType.CREATE_HOTEL_CHILD_POLICY_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/child-policy/{childPolicyId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable String hotelId, @PathVariable String childPolicyId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(CHILD_POLICY_ID, childPolicyId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_HOTEL_CHILD_POLICY);
        return perform(requestMessage, EventType.DELETE_HOTEL_CHILD_POLICY_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/child-policy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getChildPoliciesByHotelId(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_CHILD_POLICY);
        return perform(requestMessage, EventType.GET_HOTEL_CHILD_POLICY_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/child-policy/{childPolicyId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String patch(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String childPolicyId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.getEventData().put(CHILD_POLICY_ID, childPolicyId);
        requestMessage.setType(EventType.UPDATE_HOTEL_CHILD_POLICY);
        return perform(requestMessage, EventType.UPDATE_HOTEL_CHILD_POLICY_RESPONSE, request);
    }

}
