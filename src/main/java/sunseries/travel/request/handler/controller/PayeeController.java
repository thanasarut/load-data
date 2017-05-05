package sunseries.travel.request.handler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.services.TokenService;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class PayeeController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value="/accounts-payable/payees", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPayee(@RequestBody EventNotification requestMessage, org.apache.catalina.servlet4preview.http.HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.CREATE_PAYEE);
        return perform(requestMessage, EventType.CREATE_PAYEE_RESPONSE, request);
    }

    @RequestMapping(value="/accounts-payable/payees/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePayee(@PathVariable("id") String id, @RequestBody EventNotification requestMessage, org.apache.catalina.servlet4preview.http.HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(PAYEE_ID, id);
        requestMessage.setType(EventType.UPDATE_PAYEE);
        return perform(requestMessage, EventType.UPDATE_PAYEE_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payees/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePayee(@PathVariable("id") String id, org.apache.catalina.servlet4preview.http.HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PAYEE_ID, id);
        EventNotification eventNotification = createEventDataMap(map);
        eventNotification.setType(EventType.DELETE_PAYEE);
        return perform(eventNotification, EventType.DELETE_PAYEE_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPayee(@PathVariable("id") String id, org.apache.catalina.servlet4preview.http.HttpServletRequest request)
            throws InterruptedException, ClassNotFoundException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PAYEE_ID, id);
        EventNotification eventNotification = createEventDataMap(map);
        eventNotification.setType(EventType.GET_PAYEE);
        return perform(eventNotification, EventType.GET_PAYEE_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllPayee(org.apache.catalina.servlet4preview.http.HttpServletRequest request) throws InterruptedException, ClassNotFoundException, IOException {
        EventNotification eventNotification = createEventNotification();
        eventNotification.setType(EventType.GET_ALL_PAYEE);
        return perform(eventNotification, EventType.GET_ALL_PAYEE_RESPONSE, request);
    }

}