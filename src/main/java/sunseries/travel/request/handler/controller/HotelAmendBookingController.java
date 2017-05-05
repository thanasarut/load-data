package sunseries.travel.request.handler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.AccessToken;
import sunseries.travel.request.handler.services.TokenService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HotelAmendBookingController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value="/hotel-bookings/{bookingId}/guests", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amendGuests(@RequestBody EventNotification eventNotification, HttpServletRequest request,
                              @PathVariable("bookingId") String bookingId)
            throws InterruptedException {

        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.setType(EventType.AMEND_GUESTS);
        return perform(eventNotification, EventType.AMEND_GUESTS_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/bed_type", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amendRoomType(@RequestBody EventNotification eventNotification, HttpServletRequest request,
                              @PathVariable("bookingId") String bookingId)
            throws InterruptedException {

        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.setType(EventType.AMEND_BED_TYPE);
        return perform(eventNotification, EventType.AMEND_BED_TYPE_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/remark", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amendRemark(@RequestParam String token, @RequestBody EventNotification eventNotification, HttpServletRequest request,
                                @PathVariable("bookingId") String bookingId)
            throws InterruptedException {

        AccessToken accesstoken = tokenService.findAccessToken(token);
        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.getEventData().put("user_id", accesstoken.getEmail());
        eventNotification.setType(EventType.AMEND_REMARK);
        return perform(eventNotification, EventType.AMEND_REMARK_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/cancellation_deadline", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amendCancellationDate(@RequestBody EventNotification eventNotification, HttpServletRequest request,
                                @PathVariable("bookingId") String bookingId)
            throws InterruptedException {

        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.setType(EventType.AMEND_CANCELLATION_DEADLINE);
        return perform(eventNotification, EventType.AMEND_CANCELLATION_DEADLINE_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/flight_information", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amendFlightInformation(@RequestBody EventNotification eventNotification, HttpServletRequest request,
                                        @PathVariable("bookingId") String bookingId)
            throws InterruptedException {

        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.setType(EventType.AMEND_FLIGHT_INFORMATION);
        return perform(eventNotification, EventType.AMEND_FLIGHT_INFORMATION_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/hotel_confirmation_number", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amendHotelConfirmationNumber(@RequestBody EventNotification eventNotification, HttpServletRequest request,
                                         @PathVariable("bookingId") String bookingId)
            throws InterruptedException {

        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.setType(EventType.AMEND_HOTEL_CONFIRMATION_NUMBER);
        return perform(eventNotification, EventType.AMEND_HOTEL_CONFIRMATION_NUMBER_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/resend_email", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String resendBookingEmail(@RequestBody(required = false) EventNotification requestBody,
                                     HttpServletRequest request,
                                     @PathVariable("bookingId") String bookingId)
            throws InterruptedException {
        EventNotification eventNotification = new EventNotification();
        eventNotification.getEventData().put("booking_id", bookingId);

        if (requestBody != null && requestBody.getEventData().get("recipient") != null) {
            eventNotification.getEventData().put("recipient", requestBody.getEventData().get("recipient"));
        }

        eventNotification.setType(EventType.RESEND_EMAIL_BOOKING);
        return perform(eventNotification, EventType.RESEND_EMAIL_BOOKING_RESPONSE, request);
    }


    @RequestMapping(value="/hotel-bookings/{bookingId}/override_price", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String overrideBookingPrice(@RequestBody EventNotification eventNotification,
                                       HttpServletRequest request,
                                     @PathVariable("bookingId") String bookingId)
            throws InterruptedException {
        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.setType(EventType.OVERRIDE_BOOKING_PRICE);
        return perform(eventNotification, EventType.OVERRIDE_BOOKING_PRICE_RESPONSE, request);
    }

    @RequestMapping(value="/hotel-bookings/{bookingId}/agent", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amendAgentId(@RequestBody EventNotification eventNotification, HttpServletRequest request,
                                     @PathVariable("bookingId") String bookingId)
            throws InterruptedException {
        eventNotification.getEventData().put("booking_id", bookingId);
        eventNotification.setType(EventType.AMEND_AGENT_ID);
        return perform(eventNotification, EventType.AMEND_AGENT_ID_RESPONSE, request);
    }

}
