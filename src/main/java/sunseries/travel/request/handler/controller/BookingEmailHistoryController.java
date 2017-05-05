package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by vick.thanasarut on 3/29/2017 AD.
 */
@RestController
public class BookingEmailHistoryController extends BaseController {

    @RequestMapping(value="/hotel-bookings/{bookingId}/email-histories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchByBookingId(@PathVariable String bookingId, HttpServletRequest request)
            throws InterruptedException {

        HashMap<String, Object> map = new HashMap<>();
        map.put("booking", bookingId);

        EventNotification eventNotification = new EventNotification();
        eventNotification.setId(UUID.randomUUID().toString());
        eventNotification.setTtid(UUID.randomUUID().toString());
        eventNotification.setOrigin(MS_REQUEST_HANDLER);
        eventNotification.setDatetime(new Date());
        eventNotification.getEventData().put("criteria", map);
        eventNotification.setType(EventType.SEARCH_EMAIL_SENT_HISTORY);
        return perform(eventNotification, EventType.SEARCH_EMAIL_SENT_HISTORY_RESPONSE, request);
    }
}

