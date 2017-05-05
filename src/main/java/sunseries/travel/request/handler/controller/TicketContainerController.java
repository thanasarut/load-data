package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class TicketContainerController extends BaseController {

    @RequestMapping(value="/ticket-containers",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, HttpServletRequest request) throws InterruptedException {
        requestMessage.setType(EventType.CREATE_TICKET_CONTAINER);
        return perform(requestMessage, EventType.CREATE_TICKET_CONTAINER_RESPONSE, request);
    }

    @RequestMapping(value="/ticket-containers/{containerId}",  method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody EventNotification requestMessage, @PathVariable String containerId, HttpServletRequest request) throws InterruptedException {
        requestMessage.setId(UUID.randomUUID().toString());
        requestMessage.setType(EventType.UPDATE_TICKET_CONTAINER);
        requestMessage.getEventData().put("ticket_container_id", containerId);
        return perform(requestMessage, EventType.UPDATE_TICKET_CONTAINER_RESPONSE, request);
    }

    @RequestMapping(value="/ticket-containers/{containerId}",  method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable String containerId, HttpServletRequest request) throws InterruptedException {
        EventNotification requestMessage = new EventNotification();
        requestMessage.setId(UUID.randomUUID().toString());
        requestMessage.setType(EventType.DELETE_TICKET_CONTAINER);
        requestMessage.getEventData().put("ticket_container_id", containerId);
        return perform(requestMessage, EventType.DELETE_TICKET_CONTAINER_RESPONSE, request);
    }

    @RequestMapping(value="/ticket-containers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllRate(HttpServletRequest request)
            throws InterruptedException {
        EventNotification eventNotification = createEventNotification();
        eventNotification.setType(EventType.GET_ALL_TICKET_CONTAINER);
        return perform(eventNotification, EventType.GET_ALL_TICKET_CONTAINER_RESPONSE, request);
    }

    @RequestMapping(value="/ticket-containers/{containerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRate(HttpServletRequest request, @PathVariable String containerId)
            throws InterruptedException {
        EventNotification eventNotification = createEventNotification();
        eventNotification.setType(EventType.GET_TICKET_CONTAINER);
        eventNotification.getEventData().put("ticket_container_id", containerId);
        return perform(eventNotification, EventType.GET_TICKET_CONTAINER_RESPONSE, request);
    }

}
