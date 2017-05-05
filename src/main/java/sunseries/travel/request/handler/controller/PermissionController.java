package sunseries.travel.request.handler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.AccessToken;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.services.TokenService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class PermissionController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value="/permissions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addPermission(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.CREATE_PERMISSION);
        return perform(requestMessage, EventType.CREATE_PERMISSION_RESPONSE, request);
    }

    @RequestMapping(value="/permissions/{email}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String editPermission(@RequestBody EventNotification requestMessage, @PathVariable String email, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.UPDATE_PERMISSION);
        requestMessage.getEventData().put("email", email);
        return perform(requestMessage, EventType.UPDATE_PERMISSION_RESPONSE, request);
    }

    @RequestMapping(value = "/permissions/{email}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePermission(@PathVariable String email, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(EMAIL, email);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.DELETE_PERMISSION);
        return perform(requestMessage, EventType.DELETE_PERMISSION_RESPONSE, request);
    }

    @RequestMapping(value = "/permissions/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPermission(@PathVariable String email, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(EMAIL, email);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_PERMISSION);
        return perform(requestMessage, EventType.GET_PERMISSION_RESPONSE, request);
    }

    @RequestMapping(value="/permissions/verify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String verifyPermission(@RequestBody EventNotification requestMessage, @RequestParam String token, HttpServletRequest request)
            throws InterruptedException {
        AccessToken accessToken = tokenService.findAccessToken(token);
        if (accessToken == null) {
            tokenService.createInvalidTokenOrExpiredMessage();
        }
        requestMessage.setType(EventType.VERIFY_PERMISSION);
        requestMessage.getEventData().put("email", accessToken.getEmail());
        return perform(requestMessage, EventType.VERIFY_PERMISSION_RESPONSE, request);
    }


}