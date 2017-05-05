package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class HotelPromotionController extends BaseController {

    @RequestMapping(value = "/hotels/{hotelId}/promotion-containers/{promotionContainerId}/promotions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPromotionContainer(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String promotionContainerId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.getEventData().put(PROMOTION_CONTAINER_ID, promotionContainerId);
        requestMessage.setType(EventType.CREATE_HOTEL_PROMOTION);
        return perform(requestMessage, EventType.CREATE_HOTEL_PROMOTION_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/promotion-containers/{promotionContainerId}/promotions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPromotionContainer(@PathVariable String hotelId, @PathVariable String promotionContainerId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(PROMOTION_CONTAINER_ID, promotionContainerId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_PROMOTION);
        return perform(requestMessage, EventType.GET_HOTEL_PROMOTION_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/promotion-containers/{promotionContainerId}/promotions/{promotionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePromotionContainer(@PathVariable String hotelId, @PathVariable String promotionContainerId, @PathVariable String promotionId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(PROMOTION_CONTAINER_ID, promotionContainerId);
        map.put(PROMOTION_ID, promotionId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_HOTEL_PROMOTION);
        return perform(requestMessage, EventType.DELETE_HOTEL_PROMOTION_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/promotion-containers/{promotionContainerId}/promotions/{promotionId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String patchPromotionContainer(@RequestBody EventNotification requestMessage, @PathVariable String hotelId,  @PathVariable String promotionContainerId, @PathVariable String promotionId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.getEventData().put(PROMOTION_CONTAINER_ID, promotionContainerId);
        requestMessage.getEventData().put(PROMOTION_ID, promotionId);
        requestMessage.setType(EventType.UPDATE_HOTEL_PROMOTION);
        return perform(requestMessage, EventType.UPDATE_HOTEL_PROMOTION_RESPONSE, request);
    }

}

