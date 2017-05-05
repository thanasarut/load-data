package sunseries.travel.request.handler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.services.TokenService;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.constant.StateAction;
import sunseries.travel.request.handler.message.AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@RestController
public class HotelBookingController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value="/hotel-bookings/quoted", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String quotedBooking(@RequestBody EventNotification eventNotification,
                                HttpServletRequest request,
                                @RequestParam String token)
            throws InterruptedException {

        AccessToken accessToken =tokenService.findAccessToken(token);
        String agentId = accessToken.getAgentId();
        String userId = accessToken.getEmail();

        eventNotification.getEventData().put("agent_id", agentId);
        eventNotification.getEventData().put("user_id", userId);
        eventNotification.setType(EventType.QUOTATION_NEW_BOOKING);
        return perform(eventNotification, EventType.QUOTATION_NEW_BOOKING_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/confirmed", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String confirmedBooking(@RequestBody EventNotification eventNotification, HttpServletRequest request,
                                   @PathVariable("bookingId") String bookingId)
            throws InterruptedException {

        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.setType(EventType.CONFIRM_BOOKING);
        return perform(eventNotification, EventType.CONFIRM_BOOKING_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/quoted/{quoted}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amendBooking(HttpServletRequest request,
                               @PathVariable("bookingId") String bookingId,
                               @PathVariable("quoted") String quoted)
            throws InterruptedException {
        EventNotification eventNotification = new EventNotification();
        String id = UUID.randomUUID().toString();
        eventNotification.setId(id);
        eventNotification.setTtid(id);
        eventNotification.setOrigin("ms-request-handler");
        eventNotification.setDatetime(new Date());
        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.getEventData().put("quoted_id", quoted);
        eventNotification.setType(EventType.CONFIRM_BOOKING);
        return perform(eventNotification, EventType.CONFIRM_BOOKING_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{searchId}/recalculated", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String recalculateBooking(@RequestBody EventNotification eventNotification,
                                     HttpServletRequest request,
                                     @PathVariable("searchId") String searchId,
                                     @RequestParam String token)
            throws InterruptedException {

        AccessToken accessToken =tokenService.findAccessToken(token);
        String agentId = accessToken.getAgentId();
        String userId = accessToken.getEmail();

        eventNotification.getEventData().put("agent_id", agentId);
        eventNotification.getEventData().put("user_id", userId);
        eventNotification.getEventData().put("search_id", searchId);

        eventNotification.setOrigin("ms-request-handler");
        eventNotification.setDatetime(new Date());
        eventNotification.setType(EventType.BOOKING_RECALCULATE);
        return perform(eventNotification, EventType.BOOKING_RECALCULATE_RESPONSE, request);
    }


    @RequestMapping(value="/hotel-bookings/{bookingId}/state/confirm", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String confirm(@PathVariable("bookingId") String bookingId,
                                 HttpServletRequest request)
            throws InterruptedException {

        return updateBookingState(request, bookingId, StateAction.CONFIRM);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/state/guarantee", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String guarantee(@PathVariable("bookingId") String bookingId,
                                  HttpServletRequest request)
            throws InterruptedException {

        return updateBookingState(request, bookingId, StateAction.GUARANTEE);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/state/cancel", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String cancel(@PathVariable("bookingId") String bookingId,
                                 HttpServletRequest request)
            throws InterruptedException {

        return updateBookingState(request, bookingId, StateAction.CANCEL);
    }


    @RequestMapping(value="/hotel-bookings/{bookingId}/state/reject", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String reject(@PathVariable("bookingId") String bookingId,
                                             HttpServletRequest request)
            throws InterruptedException {

        return updateBookingState(request, bookingId, StateAction.REJECT);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/state/wait_for_confirm", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String waitForConfirm(@PathVariable("bookingId") String bookingId,
                                             HttpServletRequest request)
            throws InterruptedException {

        return updateBookingState(request, bookingId, StateAction.WAIT_FOR_CONFIRM);
    }

    @RequestMapping(value="/hotel-bookings/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String search(@RequestBody EventNotification requestMessage, org.apache.catalina.servlet4preview.http.HttpServletRequest request)
            throws InterruptedException {

        requestMessage.setType(EventType.BOOKING_SEARCH_BY_CRITERIA);

        return perform(requestMessage, EventType.BOOKING_SEARCH_BY_CRITERIA_RESPONSE, request);
    }

    @RequestMapping(value = "/hotel-bookings/{bookingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBooking(@PathVariable String bookingId, javax.servlet.http.HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(BOOKING_ID, bookingId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_BOOKING);
        return perform(requestMessage, EventType.GET_BOOKING_RESPONSE, request);
    }

    private String updateBookingState(HttpServletRequest request, String bookingId, StateAction stateAction) throws InterruptedException {
        EventNotification eventNotification = new EventNotification();
        String id = UUID.randomUUID().toString();
        eventNotification.setId(id);
        eventNotification.setTtid(id);
        eventNotification.setDatetime(new Date());
        eventNotification.setOrigin(MS_REQUEST_HANDLER);
        eventNotification.setType(EventType.UPDATE_BOOKING_STATE);

        HashMap<String, Object> map = new HashMap<>();
        map.put(BOOKING_ID, bookingId);
        map.put(ACTION, stateAction);
        eventNotification.setEventData(map);
        return perform(eventNotification, EventType.UPDATE_BOOKING_STATE_RESPONSE, request);
    }

}
