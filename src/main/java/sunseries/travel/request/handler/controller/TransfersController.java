package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class TransfersController extends BaseController {

    @RequestMapping(value = "/transfers/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String search(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> eventData = requestMessage.getEventData();
        eventData.put("type", EventType.TRANSFERS_SEARCH);
        requestMessage.setEventData(eventData);
        return perform(requestMessage, EventType.TRANSFERS_SEARCH_RESPONSE, request);
    }

    @RequestMapping(value = "/transfers/booking", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String booking(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        return perform(requestMessage, EventType.TRANSFERS_BOOKING_CREATE_RESPONSE, request);
    }

    @RequestMapping(value = "/transfers/booking", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amendBooking(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        return perform(requestMessage, EventType.TRANSFERS_BOOKING_UPDATE_RESPONSE, request);
    }

    @RequestMapping(value = "/transfers/booking/status", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateStatus(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        return perform(requestMessage, EventType.TRANSFERS_BOOKING_UPDATE_STATUS_RESPONSE, request);
    }

    @RequestMapping(value = "/transfers/booking/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchBooking(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {

        requestMessage.setType(EventType.TRANSFERS_BOOKING_SEARCH);
        return perform(requestMessage, EventType.TRANSFERS_BOOKING_SEARCH_RESPONSE, request);
    }

}
