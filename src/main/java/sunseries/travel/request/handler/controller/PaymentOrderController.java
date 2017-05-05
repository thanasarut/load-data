package sunseries.travel.request.handler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;
import sunseries.travel.request.handler.services.TokenService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class PaymentOrderController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/accounts-payable/payment-orders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPaymentOrder(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.NEW_PAYMENT_ORDER);
        return perform(requestMessage, EventType.NEW_PAYMENT_ORDER_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payment-orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPaymentOrderByHotelId(@RequestParam(name = "hotel_id") String hotelId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_ALL_PAYMENT_ORDER);
        return perform(requestMessage, EventType.GET_ALL_PAYMENT_ORDER_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payment-orders/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPaymentOrder(@PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PAYMENT_ORDER_ID, id);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_PAYMENT_ORDER);
        return perform(requestMessage, EventType.GET_PAYMENT_ORDER_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payment-orders/{id}/payments", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String makePaymentOrder(@RequestBody EventNotification requestMessage, @PathVariable String id, HttpServletRequest request,  @RequestParam String token)
            throws InterruptedException {
        requestMessage.getEventData().put(PAYMENT_ORDER_ID, id);
        requestMessage.getEventData().put(PAYMENT_BY, tokenService.findAccessToken(token).getEmail());
        requestMessage.setType(EventType.UPDATE_PAYMENT_ORDER);
        return perform(requestMessage, EventType.UPDATE_PAYMENT_ORDER_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payment-orders/{id}/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPaymentOrderStatus(@PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PAYMENT_ORDER_ID, id);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_PAYMENT_ORDER_STATUS);
        return perform(requestMessage, EventType.GET_PAYMENT_ORDER_STATUS_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payment-orders/{id}/status/{status}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePaymentOrderStatus(@PathVariable String id, @PathVariable String status, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PAYMENT_ORDER_ID, id);
        map.put(PAYMENT_ORDER_STATUS, status);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.UPDATE_PAYMENT_ORDER_STATUS);
        return perform(requestMessage, EventType.UPDATE_PAYMENT_ORDER_STATUS_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payment-orders/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteBookingFromPaymentOrder(@RequestBody EventNotification requestMessage, @PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(PAYMENT_ORDER_ID, id);
        requestMessage.setType(EventType.DELETE_BOOKING_FROM_PAYMENT_ORDER);
        return perform(requestMessage, EventType.DELETE_BOOKING_FROM_PAYMENT_ORDER_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payment-orders/{id}/resend-email", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String resendEmail(@RequestBody EventNotification requestMessage, @PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(PAYMENT_ORDER_ID, id);
        requestMessage.setType(EventType.RESEND_EMAIL_PAYMENT_ORDER);
        return perform(requestMessage, EventType.RESEND_EMAIL_PAYMENT_ORDER_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-payable/payment-orders/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchPaymentOrder(@RequestParam(name = "hotel_id", required = false) String hotelId,
                                   @RequestParam(name = "status",   required = false) String status,
                                   @RequestParam(name = "payment_order_id",   required = false) String paymentOrderId,
                                   @RequestParam(name = "page_no",      required = false) String pageNo,
                                   @RequestParam(name = "page_size",    required = false) String pageSize,
                                   HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(HOTEL_ID, hotelId);
        map.put(PAYMENT_ORDER_STATUS, status);
        map.put(PAYMENT_ORDER_ID, paymentOrderId);
        map.put(PAGE_NO, pageNo);
        map.put(PAGE_SIZE, pageSize);

        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.SEARCH_PAYMENT_ORDER);
        return perform(requestMessage, EventType.SEARCH_PAYMENT_ORDER_RESPONSE, request);
    }

}