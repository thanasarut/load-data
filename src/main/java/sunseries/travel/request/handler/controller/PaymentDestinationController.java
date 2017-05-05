package sunseries.travel.request.handler.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class PaymentDestinationController extends BaseController {

    @RequestMapping(value="/accounts-receivable/payment-destinations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPaymentDestination(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.CREATE_PAYMENT_DESTINATION);
        return perform(requestMessage, EventType.CREATE_PAYMENT_DESTINATION_RESPONSE, request);
    }

    @RequestMapping(value="/accounts-receivable/payment-destinations/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePaymentDestination(@PathVariable("id") String id, @RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(PAYMENT_DESTINATION_ID, id);
        requestMessage.setType(EventType.UPDATE_PAYMENT_DESTINATION);
        return perform(requestMessage, EventType.UPDATE_PAYMENT_DESTINATION_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/payment-destinations/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePaymentDestination(@PathVariable("id") String id, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PAYMENT_DESTINATION_ID, id);
        EventNotification eventNotification = createEventDataMap(map);
        eventNotification.setType(EventType.DELETE_PAYMENT_DESTINATION);
        return perform(eventNotification, EventType.DELETE_PAYMENT_DESTINATION_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/payment-destinations/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPaymentDestination(@PathVariable("id") String id, HttpServletRequest request)
            throws InterruptedException, ClassNotFoundException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PAYMENT_DESTINATION_ID, id);
        EventNotification eventNotification = createEventDataMap(map);
        eventNotification.setType(EventType.GET_PAYMENT_DESTINATION);
        return perform(eventNotification, EventType.GET_PAYMENT_DESTINATION_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/payment-destinations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllPaymentDestination(HttpServletRequest request) throws InterruptedException, ClassNotFoundException, IOException {
        EventNotification eventNotification = createEventNotification();
        eventNotification.setType(EventType.GET_ALL_PAYMENT_DESTINATION);
        return perform(eventNotification, EventType.GET_ALL_PAYMENT_DESTINATION_RESPONSE, request);
    }

}
