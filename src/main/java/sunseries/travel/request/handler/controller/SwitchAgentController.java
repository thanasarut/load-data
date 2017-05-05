package sunseries.travel.request.handler.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import java.util.HashMap;

@RestController
public class SwitchAgentController extends BaseController {

    @RequestMapping(value="/users/switch-agent", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String switchAgentId(@RequestParam(name="agent_id") String agentId, @RequestParam String token, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("agent_id", agentId);
        map.put("token", token);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.SWITCH_AGENT_ID);
        return perform(requestMessage, EventType.SWITCH_AGENT_ID_RESPONSE, request);
    }

}
