package sunseries.travel.request.handler.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.utility.DateUtils;
import sunseries.travel.request.handler.constant.EventType;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RestController
public class UserController extends BaseController {

    @RequestMapping(value="/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.CREATE_USER);
        return perform(requestMessage, EventType.CREATE_USER_RESPONSE, request);
    }

    @RequestMapping(value="/users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.UPDATE_USER);
        return perform(requestMessage, EventType.UPDATE_USER_RESPONSE, request);
    }

    @RequestMapping(value = "/users/{email}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@PathVariable("email") String email, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("email", email);
        EventNotification eventNotification = createEventMessage(EventType.DELETE_USER, map);
        return perform(eventNotification, EventType.DELETE_USER_RESPONSE, request);
    }

    @RequestMapping(value = "/users/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserByEmail(@PathVariable("email") String email, HttpServletRequest request) throws InterruptedException, ClassNotFoundException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("email", email);
        EventNotification eventNotification = createEventMessage(EventType.SEARCH_USER_BY_EMAIL, map);
        return perform(eventNotification, EventType.SEARCH_USER_BY_EMAIL_RESPONSE, request);
    }

    @RequestMapping(value="/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAllUser(HttpServletRequest request)
            throws InterruptedException {
        EventNotification eventNotification = createEventMessage(EventType.GET_ALL_USER, null);
        return perform(eventNotification, EventType.GET_ALL_USER_RESPONSE, request);
    }

    @RequestMapping(value = "/users/agent/{agentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUsersByAgentId(@PathVariable("agentId") String agentId, HttpServletRequest request) throws InterruptedException, ClassNotFoundException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("agent_id", agentId);
        EventNotification eventNotification = createEventMessage(EventType.SEARCH_USERS_BY_AGENT_ID, map);
        return perform(eventNotification, EventType.SEARCH_USERS_BY_AGENT_ID_RESPONSE, request);
    }

    private EventNotification createEventMessage(String eventType, HashMap<String, Object> map) {
        EventNotification eventNotification = new EventNotification();
        String ttid = UUID.randomUUID().toString();
        eventNotification.setId(ttid);
        eventNotification.setTtid(ttid);
        eventNotification.setDatetime(DateUtils.currentDateTimeWithUTC());
        eventNotification.setType(eventType);
        eventNotification.setEventData(map);
        return eventNotification;
    }

}
