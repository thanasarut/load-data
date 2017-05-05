package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class HotelPromotionContainerController extends BaseController {

    @RequestMapping(value = "/hotels/{hotelId}/promotion-containers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPromotionContainer(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.setType(EventType.CREATE_HOTEL_PROMOTION_CONTAINER);
        return perform(requestMessage, EventType.CREATE_HOTEL_PROMOTION_CONTAINER_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/promotion-containers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPromotionContainer(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_PROMOTION_CONTAINER);
        return perform(requestMessage, EventType.GET_HOTEL_PROMOTION_CONTAINER_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/promotion-containers/{promotionContainerId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePromotionContainer(@PathVariable String hotelId, @PathVariable String promotionContainerId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(PROMOTION_CONTAINER_ID, promotionContainerId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_HOTEL_PROMOTION_CONTAINER);
        return perform(requestMessage, EventType.DELETE_HOTEL_PROMOTION_CONTAINER_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/promotion-containers/{promotionContainerId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String patchPromotionContainer(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String promotionContainerId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.getEventData().put(PROMOTION_CONTAINER_ID, promotionContainerId);
        requestMessage.setType(EventType.UPDATE_HOTEL_PROMOTION_CONTAINER);
        return perform(requestMessage, EventType.UPDATE_HOTEL_PROMOTION_CONTAINER_RESPONSE, request);
    }

}
