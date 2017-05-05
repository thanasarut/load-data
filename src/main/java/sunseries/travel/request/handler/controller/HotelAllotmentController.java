package sunseries.travel.request.handler.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;

@RestController
public class HotelAllotmentController extends BaseController {

    @RequestMapping(value = "/hotels/{hotelId}/room-classes/{roomClassId}/allotments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String newHotelAllotment(@RequestBody EventNotification requestMessage,
                                    @PathVariable String hotelId,
                                    @PathVariable String roomClassId,
                                    HttpServletRequest request)
            throws InterruptedException {
        String parentId = hotelId+"-"+roomClassId;
        requestMessage.setType(EventType.NEW_HOTEL_ALLOTMENT);
        requestMessage.getEventData().put(PARENT_ID, parentId);
        return perform(requestMessage, EventType.NEW_HOTEL_ALLOTMENT_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/room-classes/{roomClassId}/allotments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHotelAllotment(@PathVariable String hotelId, @PathVariable String roomClassId, HttpServletRequest request)
            throws InterruptedException {
        String parentId = hotelId+"-"+roomClassId;
        HashMap<String, Object> map = new HashMap<>();
        map.put(PARENT_ID, parentId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_ALLOTMENT);
        return perform(requestMessage, EventType.GET_HOTEL_ALLOTMENT_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/room-classes/{roomClassId}/promotion-containers/{promotionContainerId}/allotments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String newHotelPromotionAllotment(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String roomClassId, @PathVariable String promotionContainerId, HttpServletRequest request)
            throws InterruptedException {
        String parentId = hotelId+"-"+roomClassId+"-"+promotionContainerId;
        requestMessage.setType(EventType.NEW_HOTEL_ALLOTMENT);
        requestMessage.getEventData().put(PARENT_ID, parentId);
        return perform(requestMessage, EventType.NEW_HOTEL_ALLOTMENT_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/room-classes/{roomClassId}/promotion-containers/{promotionContainerId}/allotments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHotelPromotionAllotment(@PathVariable String hotelId, @PathVariable String roomClassId, @PathVariable String promotionContainerId, HttpServletRequest request)
            throws InterruptedException {
        String parentId = hotelId+"-"+roomClassId+"-"+promotionContainerId;
        HashMap<String, Object> map = new HashMap<>();
        map.put(PARENT_ID, parentId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_ALLOTMENT);
        return perform(requestMessage, EventType.GET_HOTEL_ALLOTMENT_RESPONSE, request);
    }

}
