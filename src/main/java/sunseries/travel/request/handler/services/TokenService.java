package sunseries.travel.request.handler.services;

import com.google.gson.GsonBuilder;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sunseries.travel.request.handler.constant.Constant;
import sunseries.travel.request.handler.message.ErrorMessage;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.utility.DateUtils;
import sunseries.travel.request.handler.utility.GenerateUUID;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.controller.RestResponseEntityExceptionHandler;
import sunseries.travel.request.handler.exception.FailureValidationException;
import sunseries.travel.request.handler.message.AccessToken;
import sunseries.travel.request.handler.repository.RedisRepository;

import java.util.HashMap;

@Component
public class TokenService {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private ProducerTemplate producerTemplate;


    public boolean createInvalidTokenOrExpiredMessage() {
        ErrorMessage errorMessage = new ErrorMessage(RestResponseEntityExceptionHandler.FAILURE, RestResponseEntityExceptionHandler.INVALID_TOKEN_MESSAGE);
        throw new FailureValidationException(errorMessage);
    }

    public AccessToken findAccessToken(String token) {
        //if (1==1) return new AccessToken();
        String jsonString = redisRepository.getData(token);
        if (!StringUtils.isEmpty(jsonString)) {
            AccessToken accessToken = new GsonBuilder().create().fromJson(jsonString, AccessToken.class);
            raiseEvent(createVerifyTokenEventNotification(token).toJSONString());
            return accessToken;
        }
        return null;
    }

    public  void raiseEvent(String requestMessage) {
        //1. Rest the event
        producerTemplate.sendBody(Constant.CMQ_PRODUCER_ENDPOINT, requestMessage.getBytes());
    }

    private EventNotification createVerifyTokenEventNotification(String token) {
        String id = GenerateUUID.generate();
        EventNotification eventNotification = new EventNotification();
        eventNotification.setId(id);
        eventNotification.setTtid(id);
        eventNotification.setDatetime(DateUtils.currentDateTimeWithUTC());
        eventNotification.setType(EventType.VERIFY_TOKEN);

        HashMap<String, Object> eventData = new HashMap<>();
        eventData.put("token", token);
        eventNotification.setEventData(eventData);
        return eventNotification;
    }
}
