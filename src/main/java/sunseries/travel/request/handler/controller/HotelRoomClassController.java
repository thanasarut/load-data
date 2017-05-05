package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class HotelRoomClassController extends BaseController {

    @RequestMapping(value="/hotels/{hotelId}/room-classes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createRoomClass(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.setType(EventType.CREATE_ROOM_CLASS);
        return perform(requestMessage, EventType.CREATE_ROOM_CLASS_RESPONSE, request);
    }

    @RequestMapping(value="/hotels/{hotelId}/room-classes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllRoomClass(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.setType(EventType.GET_ALL_ROOM_CLASS);
        return perform(requestMessage, EventType.GET_ALL_ROOM_CLASS_RESPONSE, request);
    }

    @RequestMapping(value="/hotels/{hotelId}/room-classes/{roomClassId}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String patchRoomClass(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String roomClassId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.getEventData().put(ROOM_CLASS_ID, roomClassId);
        requestMessage.setType(EventType.UPDATE_ROOM_CLASS);
        return perform(requestMessage, EventType.UPDATE_ROOM_CLASS_RESPONSE, request);
    }

    @RequestMapping(value="/hotels/{hotelId}/room-classes/{roomClassId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletehRoomClass(@PathVariable String hotelId, @PathVariable String roomClassId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(ROOM_CLASS_ID, roomClassId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_ROOM_CLASS);
        return perform(requestMessage, EventType.DELETE_ROOM_CLASS_RESPONSE, request);
    }

    @RequestMapping(value="/hotels/{hotelId}/room-classes/{roomClassId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRoomClass(@PathVariable String hotelId, @PathVariable String roomClassId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(ROOM_CLASS_ID, roomClassId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_ROOM_CLASS);
        return perform(requestMessage, EventType.GET_ROOM_CLASS_RESPONSE, request);
    }

}
