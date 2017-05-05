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
public class AgentController extends BaseController  {


    @RequestMapping(value="/agents", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createAgent(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.CREATE_AGENT);
        return perform(requestMessage, EventType.CREATE_AGENT_RESPONSE, request);
    }

    @RequestMapping(value="/agents", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateAgent(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.UPDATE_AGENT);
        return perform(requestMessage, EventType.UPDATE_AGENT_RESPONSE, request);
    }

    @RequestMapping(value = "/agents/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteAgent(@PathVariable("id") String id, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        EventNotification eventNotification = createEventMessage(EventType.DELETE_AGENT, map);
        return perform(eventNotification, EventType.DELETE_AGENT_RESPONSE, request);
    }

    @RequestMapping(value = "/agents/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAgentById(@PathVariable("id") String id, HttpServletRequest request) throws InterruptedException, ClassNotFoundException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        EventNotification eventNotification = createEventMessage(EventType.GET_AGENT_BY_ID, map);
        return perform(eventNotification, EventType.GET_AGENT_BY_ID_RESPONSE, request);
    }

    @RequestMapping(value = "/agents/{id}/ledger", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAgentLedgerById(@PathVariable("id") String id, HttpServletRequest request) throws InterruptedException, ClassNotFoundException, IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("agent_id", id);
        EventNotification eventNotification = createEventMessage(EventType.AGENT_SEARCH_FINANCE, map);
        return perform(eventNotification, EventType.AGENT_SEARCH_FINANCE_RESPONSE, request);
    }

    @RequestMapping(value="/agents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAllAgent(HttpServletRequest request)
            throws InterruptedException {
        EventNotification eventNotification = createEventMessage(EventType.GET_ALL_AGENT, null);
        return perform(eventNotification, EventType.GET_ALL_AGENT_RESPONSE, request);
    }

    @RequestMapping(value="/agents/{agentId}/ledger", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateAgentLedger(@RequestBody EventNotification requestMessage,
                                    @PathVariable String agentId
                                    , HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.UPDATE_AGENT_LEDGER);
        requestMessage.getEventData().put("agent_id", agentId);
        return perform(requestMessage, EventType.UPDATE_AGENT_LEDGER_RESPONSE, request);
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
