package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class HotelStopSaleController extends BaseController {

    @RequestMapping(value = "/hotels/{hotelId}/room-class/{roomClassName}/stop-sale", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String newHotelStopSale(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String roomClassName, HttpServletRequest request)
            throws InterruptedException {
        String ownerId = hotelId+"-"+roomClassName;
        requestMessage.setType(EventType.NEW_HOTEL_STOP_SALE);
        requestMessage.getEventData().put(OWNER_ID, ownerId);
        return perform(requestMessage, EventType.NEW_HOTEL_STOP_SALE_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/room-class/{roomClassName}/stop-sale", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHotelStopSale(@PathVariable String hotelId, @PathVariable String roomClassName, HttpServletRequest request)
            throws InterruptedException {
        String ownerId = hotelId+"-"+roomClassName;
        HashMap<String, Object> map = new HashMap<>();
        map.put(OWNER_ID, ownerId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_HOTEL_STOP_SALE);
        return perform(requestMessage, EventType.GET_HOTEL_STOP_SALE_RESPONSE, request);
    }

}
