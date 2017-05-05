package sunseries.travel.request.handler.controller;

import com.google.gson.GsonBuilder;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sunseries.travel.request.handler.constant.Constant;
import sunseries.travel.request.handler.exception.FailureException;
import sunseries.travel.request.handler.message.ErrorMessage;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.exception.FailureNotFoundException;
import sunseries.travel.request.handler.exception.FailureTimeoutException;
import sunseries.travel.request.handler.repository.RedisRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/sunseries/v1")
public class BaseController {

    private static Logger log = LoggerFactory.getLogger(BaseController.class);

    protected static final String MS_REQUEST_HANDLER = "ms-request-handler";
    protected static final String EMAIL = "email";
    protected static final String HOTEL_ID = "hotel_id";
    protected static final String BASE_RATE_ID = "base_rate_id";
    protected static final String CHILD_POLICY_ID = "child_policy_id";
    protected static final String MINIMUM_NIGHT_STAY_ID = "minimum_night_stay_id";
    protected static final String OPTION_ID = "option_id";
    protected static final String ROOM_CLASS_ID = "room_class_id";
    protected static final String PARENT_ID = "parent_id";
    protected static final String OWNER_ID = "owner_id";
    protected static final String CANCELLATION_POLICY_ID = "cancellation_policy_id";
    protected static final String PROMOTION_CONTAINER_ID = "promotion_container_id";
    protected static final String PROMOTION_ID = "promotion_id";
    protected static final String MARKET_ID = "market_id";
    protected static final String RATE_PLAN_ID = "rate_plan_id";
    protected static final String AGENT_ID = "agent_id";
    protected static final String BOOKING_ID = "booking_id";
    protected static final String BOOKING_STATE = "booking_state";
    protected static final String TOKEN = "token";
    protected static final String AUDIT_LOG_TYPE = "audit_log_type";
    protected static final String REQUEST_URI = "request_uri";
    protected static final String SEDA_AUDITLOG = "seda://auditlog";
    protected static final String RETURN_RESULT = "return_result";
    protected static final String REQUEST = "request";
    protected static final String RESPONSE = "response";
    protected static final String ACTION = "action";
    protected static final String INVOICE_ID = "invoice_id";
    protected static final String INVOICE_STATUS = "invoice_status";
    protected static final String PAYMENT_DESTINATION_ID = "payment_destination_id";
    protected static final String PAYMENT_ORDER_ID = "payment_order_id";
    protected static final String PAYMENT_ORDER_STATUS = "payment_order_status";
    protected static final String PAYMENT_BY = "payment_by";
    protected static final String PAYEE_ID = "payee_id";
    protected static final String PAGE_NO = "page_no";
    protected static final String PAGE_SIZE = "page_size";

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private ProducerTemplate producerTemplate;


    protected void raiseEvent(String requestMessage) {
        //1. Rest the event
        producerTemplate.asyncRequestBody(Constant.CMQ_PRODUCER_ENDPOINT, requestMessage.getBytes());
    }

    protected String pollingResult(String ttId, String eventResponse) throws InterruptedException {
        try {
            //2.Block until success
            int i = 0;
            while (true) {
                if (i++ >= Constant.TIME_OUT * 15) {
                    ErrorMessage errorMessage = new ErrorMessage(RestResponseEntityExceptionHandler.FAILURE, RestResponseEntityExceptionHandler.TIMEOUT_MESSAGE);
                    throw new FailureTimeoutException(errorMessage);
                }
                Thread.sleep(20);
                String key = eventResponse + "::" + ttId;
                if (!Objects.isNull(redisRepository.getData(key))) {
                    String result = redisRepository.getData(key);
                    redisRepository.removeData(key);
                    return result;
                }
            }
        } catch (InterruptedException ie) {
            throw ie;
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(RestResponseEntityExceptionHandler.FAILURE, e.getMessage());
            throw new FailureException(errorMessage);
        }
    }

    protected String perform(EventNotification eventNotification, String eventType, HttpServletRequest request) throws InterruptedException {
        Instant start = Instant.now();
        eventNotification.setOrigin("ms-request-handler");
        Instant end = Instant.now();
        eventNotification.setProcessingTime(ChronoUnit.MILLIS.between(start, end));
        raiseEvent(eventNotification.toJSONString());

        //Audit log for request
        requestMessageForAuditLog(eventNotification, request);

        String result = pollingResult(eventNotification.getTtid(), eventType);
        end = Instant.now();
        log.warn("total execution times ========> " + ChronoUnit.MILLIS.between(start, end));
        return createResponseMessage(result, eventNotification, request);
    }

    private String createResponseMessage(String result, EventNotification eventNotification, HttpServletRequest request) {
        ErrorMessage errorMessage = new GsonBuilder().create().fromJson(result, ErrorMessage.class);
        if (RestResponseEntityExceptionHandler.FAILURE.equalsIgnoreCase(errorMessage.getStatus())) {
            if (RestResponseEntityExceptionHandler.NOT_FOUND_MESSAGE.equalsIgnoreCase(errorMessage.getMessage())) {
                throw new FailureNotFoundException(errorMessage);
            }
            throw new FailureException(errorMessage);
        }

        //Audit log for response
        responseMessageForAuditLog(result, eventNotification, request);

        return result;
    }

    private void requestMessageForAuditLog(EventNotification eventNotification,
                                           HttpServletRequest request) {

        if (eventNotification.getEventData() == null) {

            eventNotification.setEventData(new HashMap<>());
        }

        if (request.getParameter(TOKEN) != null) {
            eventNotification.getEventData().put(TOKEN, request.getParameter(TOKEN));
        }

        eventNotification.getEventData().put(REQUEST_URI, request.getRequestURI());

        eventNotification.getEventData().put(AUDIT_LOG_TYPE, REQUEST);
        producerTemplate.asyncRequestBody(SEDA_AUDITLOG, eventNotification.toJSONString());
    }


    private void responseMessageForAuditLog(String result,
                                            EventNotification eventNotification,
                                            HttpServletRequest request) {

        HashMap<String, Object> map = new HashMap<>();
        map.put(AUDIT_LOG_TYPE, RESPONSE);
        map.put(RETURN_RESULT, result);

        if (eventNotification.getEventData().get(TOKEN) != null) {

            map.put(TOKEN, eventNotification.getEventData().get(TOKEN));
        }

        eventNotification.getEventData().put(REQUEST_URI, request.getRequestURI());

        eventNotification.setEventData(map);
        producerTemplate.asyncRequestBody(SEDA_AUDITLOG, eventNotification.toJSONString());
    }


    protected EventNotification createEventDataMap(HashMap map) {
        EventNotification requestMessage = createEventNotification();
        requestMessage.setEventData(map);
        return requestMessage;
    }

    protected EventNotification createEventNotification() {
        EventNotification requestMessage = new EventNotification();
        String ttid = UUID.randomUUID().toString();
        requestMessage.setId(ttid);
        requestMessage.setTtid(ttid);
        requestMessage.setDatetime(new Date());
        requestMessage.setOrigin(MS_REQUEST_HANDLER);
        return requestMessage;
    }

}
