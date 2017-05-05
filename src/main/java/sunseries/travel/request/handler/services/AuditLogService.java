package sunseries.travel.request.handler.services;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sunseries.travel.request.handler.constant.Constant;
import sunseries.travel.request.handler.message.AccessToken;
import sunseries.travel.request.handler.message.EventNotification;

import java.util.HashMap;
import java.util.UUID;

@Component
public class AuditLogService {

    private static Logger log = LoggerFactory.getLogger(AuditLogService.class);

    public static String CMQ_PRODUCER_ENDPOINT = "crystalmq://producer?host={{crystal.server}}&port={{crystal.port.producer}}&topic={{crystal.topic}}";

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private TokenService tokenService;

    public void sendMessage(String token,
                            String action,
                            String sunseriesId,
                            Object rawData) {

        EventNotification eventNotification = new EventNotification();

        String id = UUID.randomUUID().toString();
        eventNotification.setId(id);
        eventNotification.setTtid(id);
        eventNotification.setType("new_audit_log");
        eventNotification.setOrigin("ms-request-handler");
        HashMap<String, Object> data = new HashMap<>();
        data.put("action", action);

        try {
            if (!StringUtils.isEmpty(token)) {
                AccessToken accessToken = tokenService.findAccessToken(token);
                data.put("user", accessToken.getEmail());
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        }

        data.put("sunseries_id", sunseriesId);

        data.put("raw_data", rawData);

        eventNotification.setEventData(data);

        log.info("AuditLog data : {} ", eventNotification);

        producerTemplate.asyncRequestBody(Constant.CMQ_PRODUCER_ENDPOINT, eventNotification.toJSONString().getBytes());
    }
}
