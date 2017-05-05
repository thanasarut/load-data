package sunseries.travel.request.handler.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class RegionPlaceController extends BaseController {

    @RequestMapping(value="/place",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPlace(@RequestBody EventNotification requestMessage, HttpServletRequest request) throws InterruptedException {
        return perform(requestMessage, EventType.NEW_PLACE_RESPONSE, request);
    }

    @RequestMapping(value="/region",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createRegion(@RequestBody EventNotification requestMessage, HttpServletRequest request) throws InterruptedException {
        return perform(requestMessage, EventType.NEW_REGION_RESPONSE, request);
    }

    @RequestMapping(value = "/place", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAllPlace(HttpServletRequest request) throws InterruptedException {
        EventNotification requestMessage = new EventNotification();
        String ttid = UUID.randomUUID().toString();
        requestMessage.setId(ttid);
        requestMessage.setTtid(ttid);
        requestMessage.setType(EventType.SEARCH_PLACE);
        return perform(requestMessage, EventType.SEARCH_PLACE_RESPONSE, request);
    }
}
