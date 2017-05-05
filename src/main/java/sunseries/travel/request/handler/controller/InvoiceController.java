package sunseries.travel.request.handler.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sunseries.travel.request.handler.constant.EventType;
import sunseries.travel.request.handler.message.EventNotification;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class InvoiceController extends BaseController {

    @RequestMapping(value = "/accounts-receivable/invoices", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createInvoice(@RequestBody EventNotification requestMessage, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.setType(EventType.NEW_INVOICE);
        return perform(requestMessage, EventType.NEW_INVOICE_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/invoices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllInvoice(@RequestParam(name = "agent_id") String agentId, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(AGENT_ID, agentId);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_ALL_INVOICE);
        return perform(requestMessage, EventType.GET_ALL_INVOICE_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/invoices/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getInvoice(@PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(INVOICE_ID, id);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_INVOICE);
        return perform(requestMessage, EventType.GET_INVOICE_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/invoices/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateInvoice(@RequestBody EventNotification requestMessage, @PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(INVOICE_ID, id);
        requestMessage.setType(EventType.UPDATE_INVOICE);
        return perform(requestMessage, EventType.UPDATE_INVOICE_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/invoices/{id}/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getInvoiceStatus(@PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(INVOICE_ID, id);
        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.GET_INVOICE_STATUS);
        return perform(requestMessage, EventType.GET_INVOICE_STATUS_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/invoices/{id}/status/{status}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateInvoiceStatus(@RequestBody(required = false) EventNotification requestMessage, @PathVariable String id, @PathVariable String status, HttpServletRequest request)
            throws InterruptedException {
        if (requestMessage != null) {
            requestMessage.getEventData().put(INVOICE_ID, id);
            requestMessage.getEventData().put(INVOICE_STATUS, status);
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put(INVOICE_ID, id);
            map.put(INVOICE_STATUS, status);
            requestMessage = createEventDataMap(map);
        }
        requestMessage.setType(EventType.UPDATE_INVOICE_STATUS);
        return perform(requestMessage, EventType.UPDATE_INVOICE_STATUS_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/invoices/{id}/payments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPayment(@RequestBody EventNotification requestMessage, @PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(INVOICE_ID, id);
        requestMessage.setType(EventType.NEW_PAYMENT);
        return perform(requestMessage, EventType.NEW_PAYMENT_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/invoices/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addBookingToInvoice(@RequestBody EventNotification requestMessage, @PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(INVOICE_ID, id);
        requestMessage.setType(EventType.ADD_BOOKING_TO_INVOICE);
        return perform(requestMessage, EventType.ADD_BOOKING_TO_INVOICE_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/invoices/{id}/resend-email", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String resendEmail(@RequestBody EventNotification requestMessage, @PathVariable String id, HttpServletRequest request)
            throws InterruptedException {
        requestMessage.getEventData().put(INVOICE_ID, id);
        requestMessage.setType(EventType.RESEND_EMAIL_INVOICE);
        return perform(requestMessage, EventType.RESEND_EMAIL_INVOICE_RESPONSE, request);
    }

    @RequestMapping(value = "/accounts-receivable/invoices/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchInvoice(@RequestParam(name = "agent_id", required = false) String agentId,
                                   @RequestParam(name = "status",   required = false) String status,
                                   @RequestParam(name = "invoice_id",   required = false) String invoiceId,
                                   @RequestParam(name = "booking_state",   required = false) String bookingState,
                                   @RequestParam(name = "page_no",      required = false) String pageNo,
                                   @RequestParam(name = "page_size",    required = false) String pageSize,
                                   HttpServletRequest request)
            throws InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(AGENT_ID, agentId);
        map.put(INVOICE_STATUS, status);
        map.put(INVOICE_ID, invoiceId);
        map.put(BOOKING_STATE, bookingState);
        map.put(PAGE_NO, pageNo);
        map.put(PAGE_SIZE, pageSize);

        EventNotification requestMessage = createEventDataMap(map);
        requestMessage.setType(EventType.SEARCH_INVOICE);
        return perform(requestMessage, EventType.SEARCH_INVOICE_RESPONSE, request);
    }

}