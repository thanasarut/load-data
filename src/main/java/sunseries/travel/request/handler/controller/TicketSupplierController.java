package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class TicketSupplierController extends BaseController {

    @RequestMapping(value="/ticket-suppliers",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, HttpServletRequest request) throws InterruptedException {

        requestMessage.setType(EventType.CREATE_TICKET_SUPPLIER);
        return perform(requestMessage, EventType.CREATE_TICKET_SUPPLIER_RESPONSE, request);
    }

    @RequestMapping(value="/ticket-suppliers/{supplierId}",  method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody EventNotification requestMessage, @PathVariable String supplierId, HttpServletRequest request) throws InterruptedException {
        requestMessage.setId(UUID.randomUUID().toString());
        requestMessage.setType(EventType.UPDATE_TICKET_SUPPLIER);
        requestMessage.getEventData().put("supplier_id", supplierId);
        return perform(requestMessage, EventType.UPDATE_TICKET_SUPPLIER_RESPONSE, request);
    }

    @RequestMapping(value="/ticket-suppliers/{supplierId}",  method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable String supplierId, HttpServletRequest request) throws InterruptedException {
        EventNotification requestMessage = new EventNotification();
        requestMessage.setId(UUID.randomUUID().toString());
        requestMessage.setType(EventType.DELETE_TICKET_SUPPLIER);
        requestMessage.getEventData().put("supplier_id", supplierId);
        return perform(requestMessage, EventType.DELETE_TICKET_SUPPLIER_RESPONSE, request);
    }

    @RequestMapping(value="/ticket-suppliers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String listAllSupplier(HttpServletRequest request)
            throws InterruptedException {
        EventNotification eventNotification = createEventNotification();
        eventNotification.setType(EventType.LIST_ALL_TICKET_SUPPLIER);
        return perform(eventNotification, EventType.LIST_ALL_TICKET_SUPPLIER_RESPONSE, request);
    }

    @RequestMapping(value="/ticket-groups/supplier/{supplierId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String listAllticketGroup(HttpServletRequest request, @PathVariable String supplierId)
            throws InterruptedException {
        EventNotification eventNotification = createEventNotification();
        eventNotification.setType(EventType.LIST_ALL_TICKET_GROUP);
        eventNotification.getEventData().put("supplier_id", supplierId);
        return perform(eventNotification, EventType.LIST_ALL_TICKET_GROUP_RESPONSE, request);
    }

}
