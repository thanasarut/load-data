package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.utility.DateUtils;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.utility.GenerateUUID;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class TariffController extends BaseController {

    @RequestMapping(value="/tariff",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, HttpServletRequest request) throws InterruptedException {
        requestMessage.getEventData().put("type", EventType.CREATE_TARIFF);
        return perform(requestMessage, EventType.CREATE_TARIFF_RESPONSE, request);
    }

    @RequestMapping(value="/tariff", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody EventNotification requestMessage, HttpServletRequest request) throws InterruptedException {
        requestMessage.getEventData().put("type", EventType.UPDATE_TARIFF);
        return perform(requestMessage, EventType.UPDATE_TARIFF_RESPONSE, request);
    }

    @RequestMapping(value="/tariff/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteById(@PathVariable String id, HttpServletRequest request) throws InterruptedException {
        EventNotification eventNotification = createEventNotification(EventType.DELETE_TARIFF_BY_ID);
        eventNotification.setEventData(createEventData(id));
        return perform(eventNotification, EventType.DELETE_TARIFF_BY_ID_RESPONSE, request);
    }

    @RequestMapping(value="/tariffs", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteAll(HttpServletRequest request) throws InterruptedException {
        EventNotification eventNotification = createEventNotification(EventType.DELETE_ALL_TARIFF);
        return perform(eventNotification, EventType.DELETE_ALL_TARIFF_RESPONSE, request);

    }

    @RequestMapping(value="/tariff/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String findByTariffId(@PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        EventNotification eventNotification = createEventNotification(EventType.SEARCH_TARIFF);
        eventNotification.setEventData(createEventData(id));
        return perform(eventNotification, EventType.SEARCH_TARIFF_RESPONSE, request);
    }

    private EventNotification createEventNotification(String type) {
        String id = GenerateUUID.generate();
        EventNotification eventNotification = new EventNotification();
        eventNotification.setId(id);
        eventNotification.setTtid(id);
        eventNotification.setDatetime(DateUtils.currentDateTimeWithUTC());
        eventNotification.setType(type);
        return eventNotification;
    }

    private HashMap<String, Object> createEventData(String id) {
        HashMap<String, Object> eventData = new HashMap<>();
        eventData.put("id", id);
        return eventData;
    }

}
