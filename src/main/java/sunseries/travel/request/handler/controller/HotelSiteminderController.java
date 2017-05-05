package sunseries.travel.request.handler.controller;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.message.Siteminder;
import sunseries.travel.request.handler.constant.EventType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class HotelSiteminderController extends BaseController {

    private static final String MARKUP_AMOUNT = "markup_amount";
    private static final String INCENTIVE_AMOUNT = "incentive_amount";
    private static final String CANCELLATION_DAYS = "cancellation_days";
    private static final String RATE_PROTECTION = "rate_protection";
    private static final String EARLY_BIRD_DAYS = "earlybird_days";

    @RequestMapping(value = "/hotels/{hotelId}/siteminder/rate-plans/{ratePlanId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String ratePlanId, HttpServletRequest request)
            throws InterruptedException {
        Gson gson = new Gson();
        String eventData = gson.toJson(requestMessage.getEventData());
        Siteminder siteminder = gson.fromJson(eventData, Siteminder.class);
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.getEventData().put(RATE_PLAN_ID, ratePlanId);
        requestMessage.getEventData().put(MARKUP_AMOUNT, siteminder.getMarkupAmount());
        requestMessage.getEventData().put(INCENTIVE_AMOUNT, siteminder.getIncentiveAmount());
        requestMessage.getEventData().put(CANCELLATION_DAYS, siteminder.getCancellationDays());
        requestMessage.getEventData().put(RATE_PROTECTION, siteminder.getRateProtection());
        requestMessage.getEventData().put(EARLY_BIRD_DAYS, siteminder.getEarlybirdDays());
        requestMessage.setType(EventType.NEW_SITEMINDER_RATE);
        return perform(requestMessage, EventType.NEW_SITEMINDER_RATE_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/siteminder/rate-plans/rates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllRate(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_ALL_SITEMINDER_RATE);
        return perform(requestMessage, EventType.GET_ALL_SITEMINDER_RATE_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/siteminder/rate-plans", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllRatePlan(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_ALL_SITEMINDER_RATE_PLAN);
        return perform(requestMessage, EventType.GET_ALL_SITEMINDER_RATE_PLAN_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/siteminder/rate-plans/{ratePlanId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody EventNotification requestMessage, @PathVariable String hotelId, @PathVariable String ratePlanId, HttpServletRequest request)
            throws InterruptedException {
        Gson gson = new Gson();
        String eventData = gson.toJson(requestMessage.getEventData());
        Siteminder siteminder = gson.fromJson(eventData, Siteminder.class);
        requestMessage.getEventData().put(HOTEL_ID, hotelId);
        requestMessage.getEventData().put(RATE_PLAN_ID, ratePlanId);
        requestMessage.getEventData().put(MARKUP_AMOUNT, siteminder.getMarkupAmount());
        requestMessage.getEventData().put(INCENTIVE_AMOUNT, siteminder.getIncentiveAmount());
        requestMessage.getEventData().put(CANCELLATION_DAYS, siteminder.getCancellationDays());
        requestMessage.getEventData().put(RATE_PROTECTION, siteminder.getRateProtection());
        requestMessage.getEventData().put(EARLY_BIRD_DAYS, siteminder.getEarlybirdDays());
        requestMessage.setType(EventType.UPDATE_SITEMINDER_RATE);
        return perform(requestMessage, EventType.UPDATE_SITEMINDER_RATE_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/siteminder/rate-plans/{ratePlanId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable String hotelId, @PathVariable String ratePlanId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(RATE_PLAN_ID, ratePlanId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_SITEMINDER_RATE);
        return perform(requestMessage, EventType.DELETE_SITEMINDER_RATE_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/siteminder/rate-plans/{ratePlanId}/rates", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String flush(@PathVariable String hotelId, @PathVariable String ratePlanId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(RATE_PLAN_ID, ratePlanId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.FLUSH_SITEMINDER_RATE);
        return perform(requestMessage, EventType.FLUSH_SITEMINDER_RATE_RESPONSE, request);
    }

    @RequestMapping(value = "/hotels/{hotelId}/siteminder/rate-plans/rates", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String flushAll(@PathVariable String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.FLUSH_ALL_SITEMINDER_RATE);
        return perform(requestMessage, EventType.FLUSH_ALL_SITEMINDER_RATE_RESPONSE, request);
    }

}
