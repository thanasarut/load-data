package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class MarketController extends BaseController {

    @RequestMapping(value = "/markets", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.NEW_MARKET);
        return perform(requestMessage, EventType.NEW_MARKET_RESPONSE, request);
    }

    @RequestMapping(value = "/markets/{marketId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody EventNotification requestMessage, @PathVariable String marketId, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(MARKET_ID, marketId);
        requestMessage.setType(EventType.MARKET_COUNTRIES_UPDATED);
        return perform(requestMessage, EventType.MARKET_COUNTRIES_UPDATED_RESPONSE, request);
    }

    @RequestMapping(value = "/markets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMarkets(HttpServletRequest request)
            throws InterruptedException {
        EventNotification requestMessage = createEventDataMap(null);
        requestMessage.setType(EventType.VIEW_MARKETS);
        return perform(requestMessage, EventType.VIEW_MARKETS_RESPONSE, request);
    }

    @RequestMapping(value = "/markets/{marketId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMarket(@PathVariable String marketId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(MARKET_ID, marketId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.VIEW_MARKET);
        return perform(requestMessage, EventType.VIEW_MARKET_RESPONSE, request);
    }

}
