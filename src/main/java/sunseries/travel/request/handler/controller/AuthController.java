package sunseries.travel.request.handler.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.constant.EventType;

@RestController
public class AuthController extends BaseController {

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String authenticate(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.AUTHENTICATION);
        return perform(requestMessage, EventType.AUTHENTICATION_RESPONSE, request);
    }

    @RequestMapping(value = "/deauthenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deauthenticate(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.DEAUTHENTICATION);
        return perform(requestMessage, EventType.DEAUTHENTICATION_RESPONSE, request);
    }

}
