package sunseries.travel.request.handler.constant;

public class EventType {

    //tariff
    public static final String CREATE_TARIFF = "create_tariff";
    public static final String CREATE_TARIFF_RESPONSE = "create_tariff_response";
    public static final String UPDATE_TARIFF = "update_tariff";
    public static final String UPDATE_TARIFF_RESPONSE = "update_tariff_response";
    public static final String DELETE_TARIFF_BY_ID = "delete_tariff_by_id";
    public static final String DELETE_TARIFF_BY_ID_RESPONSE = "delete_tariff_by_id_response";
    public static final String DELETE_ALL_TARIFF = "delete_all_tariff";
    public static final String DELETE_ALL_TARIFF_RESPONSE = "delete_all_tariff_response";
    public static final String SEARCH_TARIFF = "search_tariff";
    public static final String SEARCH_TARIFF_RESPONSE = "search_tariff_response";

    //transfers
    public static final String TRANSFERS_SEARCH = "transfers_search";
    public static final String TRANSFERS_SEARCH_RESPONSE = "transfers_search_response";
    public static final String TRANSFERS_BOOKING_CREATE_RESPONSE = "transfers_booking_create_response";
    public static final String TRANSFERS_BOOKING_UPDATE_STATUS_RESPONSE = "transfers_booking_update_status_response";
    public static final String TRANSFERS_BOOKING_UPDATE_RESPONSE = "transfers_booking_update_response";

    //permission
    public static final String CREATE_PERMISSION = "create_permission";
    public static final String CREATE_PERMISSION_RESPONSE = "create_permission_response";
    public static final String UPDATE_PERMISSION = "update_permission";
    public static final String UPDATE_PERMISSION_RESPONSE = "update_permission_response";
    public static final String DELETE_PERMISSION = "delete_permission";
    public static final String DELETE_PERMISSION_RESPONSE = "delete_permission_response";
    public static final String GET_PERMISSION    = "get_permission";
    public static final String GET_PERMISSION_RESPONSE    = "get_permission_response";
    public static final String VERIFY_PERMISSION = "verify_permission";
    public static final String VERIFY_PERMISSION_RESPONSE = "verify_permission_response";

    //auth
    public static final String VERIFY_TOKEN = "verify_token";
    public static final String AUTHENTICATION = "authentication";
    public static final String DEAUTHENTICATION = "deauthentication";
    public static final String AUTHENTICATION_RESPONSE = "authentication_response";
    public static final String VERIFY_TOKEN_RESPONSE = "verify_token_response";
    public static final String DEAUTHENTICATION_RESPONSE = "deauthentication_response";

    //user
    public static final String CREATE_USER = "create_user";
    public static final String CREATE_USER_RESPONSE = "create_user_response";
    public static final String UPDATE_USER = "update_user";
    public static final String UPDATE_USER_RESPONSE = "update_user_response";
    public static final String DELETE_USER = "delete_user";
    public static final String DELETE_USER_RESPONSE = "delete_user_response";
    public static final String SEARCH_USER_BY_EMAIL = "search_user_by_email";
    public static final String SEARCH_USER_BY_EMAIL_RESPONSE = "search_user_by_email_response";
    public static final String GET_ALL_USER = "get_all_user";
    public static final String GET_ALL_USER_RESPONSE = "get_all_user_response";
    public static final String SEARCH_USERS_BY_AGENT_ID = "search_users_by_agent_id";
    public static final String SEARCH_USERS_BY_AGENT_ID_RESPONSE = "search_users_by_agent_id_response";

    //hotel
    public static final String CREATE_HOTEL = "create_hotel";
    public static final String CREATE_HOTEL_RESPONSE = "create_hotel_response";
    public static final String UPDATE_HOTEL = "update_hotel";
    public static final String UPDATE_HOTEL_RESPONSE = "update_hotel_response";
    public static final String DELETE_HOTEL = "delete_hotel";
    public static final String DELETE_HOTEL_RESPONSE = "delete_hotel_response";
    public static final String GET_HOTEL = "get_hotel";
    public static final String GET_HOTEL_RESPONSE = "get_hotel_response";
    public static final String GET_ALL_HOTEL = "get_all_hotel";
    public static final String GET_ALL_HOTEL_RESPONSE = "get_all_hotel_response";

    //room class
    public static final String CREATE_ROOM_CLASS = "create_room_class";
    public static final String CREATE_ROOM_CLASS_RESPONSE = "create_room_class_response";
    public static final String UPDATE_ROOM_CLASS = "update_room_class";
    public static final String UPDATE_ROOM_CLASS_RESPONSE = "update_room_class_response";
    public static final String DELETE_ROOM_CLASS = "delete_room_class";
    public static final String DELETE_ROOM_CLASS_RESPONSE = "delete_room_class_response";
    public static final String GET_ROOM_CLASS = "get_room_class";
    public static final String GET_ROOM_CLASS_RESPONSE = "get_room_class_response";
    public static final String GET_ALL_ROOM_CLASS = "get_all_room_class";
    public static final String GET_ALL_ROOM_CLASS_RESPONSE = "get_all_room_class_response";

    //child policy
    public static final String CREATE_HOTEL_CHILD_POLICY = "create_hotel_child_policy";
    public static final String CREATE_HOTEL_CHILD_POLICY_RESPONSE = "create_hotel_child_policy_response";
    public static final String UPDATE_HOTEL_CHILD_POLICY = "update_hotel_child_policy";
    public static final String UPDATE_HOTEL_CHILD_POLICY_RESPONSE = "update_hotel_child_policy_response";
    public static final String DELETE_HOTEL_CHILD_POLICY = "delete_hotel_child_policy";
    public static final String DELETE_HOTEL_CHILD_POLICY_RESPONSE = "delete_hotel_child_policy_response";
    public static final String GET_HOTEL_CHILD_POLICY = "get_hotel_child_policy";
    public static final String GET_HOTEL_CHILD_POLICY_RESPONSE = "get_hotel_child_policy_response";

    //minimum night stay
    public static final String CREATE_HOTEL_MINIMUM_NIGHT_STAY = "create_hotel_minimum_night_stay";
    public static final String CREATE_HOTEL_MINIMUM_NIGHT_STAY_RESPONSE = "create_hotel_minimum_night_stay_response";
    public static final String UPDATE_HOTEL_MINIMUM_NIGHT_STAY = "update_hotel_minimum_night_stay";
    public static final String UPDATE_HOTEL_MINIMUM_NIGHT_STAY_RESPONSE = "update_hotel_minimum_night_stay_response";
    public static final String DELETE_HOTEL_MINIMUM_NIGHT_STAY = "delete_hotel_minimum_night_stay";
    public static final String DELETE_HOTEL_MINIMUM_NIGHT_STAY_RESPONSE = "delete_hotel_minimum_night_stay_response";
    public static final String GET_HOTEL_MINIMUM_NIGHT_STAY = "get_hotel_minimum_night_stay";
    public static final String GET_HOTEL_MINIMUM_NIGHT_STAY_RESPONSE = "get_hotel_minimum_night_stay_response";

    //hotels option
    public static final String CREATE_HOTEL_OPTION = "create_hotel_option";
    public static final String CREATE_HOTEL_OPTION_RESPONSE = "create_hotel_option_response";
    public static final String DELETE_HOTEL_OPTION = "delete_hotel_option";
    public static final String DELETE_HOTEL_OPTION_RESPONSE = "delete_hotel_option_response";
    public static final String GET_HOTEL_OPTION = "get_hotel_option";
    public static final String GET_HOTEL_OPTION_RESPONSE = "get_hotel_option_response";

    //stop sale
    public static final String NEW_HOTEL_STOP_SALE = "new_stopsale_dates";
    public static final String NEW_HOTEL_STOP_SALE_RESPONSE = "new_stopsale_result";
    public static final String GET_HOTEL_STOP_SALE = "get_stopsale";
    public static final String GET_HOTEL_STOP_SALE_RESPONSE = "get_stopsale_result";

    //allotment
    public static final String NEW_HOTEL_ALLOTMENT = "new_allotment";
    public static final String NEW_HOTEL_ALLOTMENT_RESPONSE = "new_allotment_result";
    public static final String GET_HOTEL_ALLOTMENT = "get_allotment";
    public static final String GET_HOTEL_ALLOTMENT_RESPONSE = "get_allotments_result";

    //base rate
    public static final String CREATE_HOTEL_BASE_RATE = "create_hotel_base_rate";
    public static final String UPDATE_HOTEL_BASE_RATE = "update_hotel_base_rate";
    public static final String DELETE_HOTEL_BASE_RATE = "delete_hotel_base_rate";
    public static final String GET_HOTEL_BASE_RATE = "get_hotel_base_rate";

    public static final String CREATE_HOTEL_BASE_RATE_RESPONSE = "create_hotel_base_rate_response";
    public static final String UPDATE_HOTEL_BASE_RATE_RESPONSE = "update_hotel_base_rate_response";
    public static final String DELETE_HOTEL_BASE_RATE_RESPONSE = "delete_hotel_base_rate_response";
    public static final String GET_HOTEL_BASE_RATE_RESPONSE = "get_hotel_base_rate_response";

    //cancellation policy
    public static final String CREATE_HOTEL_CANCELLATION_POLICY = "create_hotel_cancellation_policy";
    public static final String UPDATE_HOTEL_CANCELLATION_POLICY = "update_hotel_cancellation_policy";
    public static final String DELETE_HOTEL_CANCELLATION_POLICY = "delete_hotel_cancellation_policy";
    public static final String GET_HOTEL_CANCELLATION_POLICY = "get_hotel_cancellation_policy";

    public static final String CREATE_HOTEL_CANCELLATION_POLICY_RESPONSE = "create_hotel_cancellation_policy_response";
    public static final String UPDATE_HOTEL_CANCELLATION_POLICY_RESPONSE = "update_hotel_cancellation_policy_response";
    public static final String DELETE_HOTEL_CANCELLATION_POLICY_RESPONSE = "delete_hotel_cancellation_policy_response";
    public static final String GET_HOTEL_CANCELLATION_POLICY_RESPONSE = "get_hotel_cancellation_policy_response";

    //promotion container
    public static final String CREATE_HOTEL_PROMOTION_CONTAINER = "create_hotel_promotion_container";
    public static final String UPDATE_HOTEL_PROMOTION_CONTAINER = "update_hotel_promotion_container";
    public static final String DELETE_HOTEL_PROMOTION_CONTAINER = "delete_hotel_promotion_container";
    public static final String GET_HOTEL_PROMOTION_CONTAINER = "get_hotel_promotion_container";

    public static final String CREATE_HOTEL_PROMOTION_CONTAINER_RESPONSE = "create_hotel_promotion_container_response";
    public static final String UPDATE_HOTEL_PROMOTION_CONTAINER_RESPONSE = "update_hotel_promotion_container_response";
    public static final String DELETE_HOTEL_PROMOTION_CONTAINER_RESPONSE = "delete_hotel_promotion_container_response";
    public static final String GET_HOTEL_PROMOTION_CONTAINER_RESPONSE = "get_hotel_promotion_container_response";

    //promotion
    public static final String CREATE_HOTEL_PROMOTION = "create_hotel_promotion";
    public static final String UPDATE_HOTEL_PROMOTION = "update_hotel_promotion";
    public static final String DELETE_HOTEL_PROMOTION = "delete_hotel_promotion";
    public static final String GET_HOTEL_PROMOTION = "get_hotel_promotion";

    public static final String CREATE_HOTEL_PROMOTION_RESPONSE = "create_hotel_promotion_response";
    public static final String UPDATE_HOTEL_PROMOTION_RESPONSE = "update_hotel_promotion_response";
    public static final String DELETE_HOTEL_PROMOTION_RESPONSE = "delete_hotel_promotion_response";
    public static final String GET_HOTEL_PROMOTION_RESPONSE = "get_hotel_promotion_response";

    //market
    public static final String NEW_MARKET = "new_market";
    public static final String MARKET_COUNTRIES_UPDATED = "market_countries_updated";
    public static final String VIEW_MARKET = "view_market";
    public static final String VIEW_MARKETS = "view_markets";

    public static final String NEW_MARKET_RESPONSE = "new_market_result";
    public static final String MARKET_COUNTRIES_UPDATED_RESPONSE = "market_countries_updated_result";
    public static final String VIEW_MARKET_RESPONSE = "view_market_result";
    public static final String VIEW_MARKETS_RESPONSE = "view_markets_result";

    //hotel search
    public static final String HOTEL_SEARCH_ALL = "hotel_search_all";
    public static final String HOTEL_SEARCH_ALL_RESPONSE = "hotel_search_all_response";
    public static final String HOTEL_SEARCH_BY_CITY = "hotel_search_by_city";
    public static final String COUNT_HOTEL_SEARCH_BY_CITY = "count_hotel_search_by_city";
    public static final String COUNT_HOTEL_SEARCH_BY_CITY_RESPONSE = "count_hotel_search_by_city_response";


    //place
    public static final String SEARCH_PLACE = "search_place";
    public static final String SEARCH_PLACE_RESPONSE = "search_place_response";
    public static final String NEW_PLACE_RESPONSE = "new_place_response";
    public static final String NEW_REGION_RESPONSE = "new_region_response";

    //Transfer Booking Search
    public static final String TRANSFERS_BOOKING_SEARCH = "transfers_booking_search";
    public static final String TRANSFERS_BOOKING_SEARCH_RESPONSE = "transfers_booking_search_response";

    //Agent
    public static final String CREATE_AGENT = "create_agent";
    public static final String CREATE_AGENT_RESPONSE = "create_agent_response";
    public static final String UPDATE_AGENT = "update_agent";
    public static final String UPDATE_AGENT_RESPONSE = "update_agent_response";
    public static final String DELETE_AGENT = "delete_agent";
    public static final String DELETE_AGENT_RESPONSE = "delete_agent_response";
    public static final String GET_AGENT_BY_ID = "get_agent_by_id";
    public static final String GET_AGENT_BY_ID_RESPONSE = "get_agent_by_id_response";
    public static final String GET_ALL_AGENT ="get_all_agent";
    public static final String GET_ALL_AGENT_RESPONSE ="get_all_agent_response";
    public static final String UPDATE_AGENT_LEDGER = "update_agent_ledger";
    public static final String UPDATE_AGENT_LEDGER_RESPONSE = "update_agent_ledger_response";
    public static final String AGENT_SEARCH_FINANCE = "agent_search_finance";
    public static final String AGENT_SEARCH_FINANCE_RESPONSE = "agent_search_finance_response";

    //siteminder rate plans
    public static final String NEW_SITEMINDER_RATE = "new_siteminder_rateplan";
    public static final String UPDATE_SITEMINDER_RATE = "update_siteminder_rateplan";
    public static final String DELETE_SITEMINDER_RATE = "delete_siteminder_rateplan";
    public static final String FLUSH_SITEMINDER_RATE  = "flush_siteminder_rateplan";
    public static final String FLUSH_ALL_SITEMINDER_RATE  = "flush_siteminder_hotel";
    public static final String GET_ALL_SITEMINDER_RATE = "all_siteminder_data";
    public static final String GET_ALL_SITEMINDER_RATE_PLAN = "all_siteminder_rateplans";

    public static final String NEW_SITEMINDER_RATE_RESPONSE = "new_siteminder_rateplan_response";
    public static final String UPDATE_SITEMINDER_RATE_RESPONSE = "update_siteminder_rateplan_response";
    public static final String DELETE_SITEMINDER_RATE_RESPONSE = "delete_siteminder_rateplan_response";
    public static final String FLUSH_SITEMINDER_RATE_RESPONSE  = "flush_siteminder_rateplan_response";
    public static final String FLUSH_ALL_SITEMINDER_RATE_RESPONSE  = "flush_siteminder_hotel_response";
    public static final String GET_ALL_SITEMINDER_RATE_RESPONSE = "all_siteminder_data_response";
    public static final String GET_ALL_SITEMINDER_RATE_PLAN_RESPONSE = "all_siteminder_rateplans_response";

    //Booking
    public static final String QUOTATION_NEW_BOOKING = "quotation_new_booking";
    public static final String QUOTATION_NEW_BOOKING_RESPONSE = "quotation_new_booking_response";
    public static final String CONFIRM_BOOKING = "confirm_booking";
    public static final String CONFIRM_BOOKING_RESPONSE = "confirm_booking_response";
    public static final String UPDATE_BOOKING_STATE = "update_booking_state";
    public static final String UPDATE_BOOKING_STATE_RESPONSE = "update_booking_state_response";

    //Amend booking
    public static final String AMEND_GUESTS = "amend_guests";
    public static final String AMEND_GUESTS_RESPONSE = "amend_guests_response";
    public static final String AMEND_BED_TYPE = "amend_bed_type";
    public static final String AMEND_BED_TYPE_RESPONSE = "amend_bed_type_response";
    public static final String AMEND_REMARK = "amend_remark";
    public static final String AMEND_REMARK_RESPONSE = "amend_remark_response";
    public static final String AMEND_CANCELLATION_DEADLINE = "amend_cancellation_deadline";
    public static final String AMEND_CANCELLATION_DEADLINE_RESPONSE = "amend_cancellation_deadline_response";
    public static final String AMEND_FLIGHT_INFORMATION = "amend_flight_information";
    public static final String AMEND_FLIGHT_INFORMATION_RESPONSE = "amend_flight_information_response";
    public static final String AMEND_HOTEL_CONFIRMATION_NUMBER = "amend_hotel_confirmation_number";
    public static final String AMEND_HOTEL_CONFIRMATION_NUMBER_RESPONSE = "amend_hotel_confirmation_number_response";
    public static final String RESEND_EMAIL_BOOKING = "resend_email_booking";
    public static final String RESEND_EMAIL_BOOKING_RESPONSE = "resend_email_booking_response";
    public static final String OVERRIDE_BOOKING_PRICE = "override_booking_price";
    public static final String OVERRIDE_BOOKING_PRICE_RESPONSE = "override_booking_price_response";
    public static final String AMEND_AGENT_ID = "amend_agent_id";
    public static final String AMEND_AGENT_ID_RESPONSE = "amend_agent_id_response";

    //invoice
    public static final String NEW_INVOICE = "new_invoice";
    public static final String UPDATE_INVOICE = "update_invoice";
    public static final String GET_INVOICE = "get_invoice";
    public static final String GET_ALL_INVOICE = "get_all_invoice";
    public static final String GET_INVOICE_STATUS = "get_invoice_status";
    public static final String UPDATE_INVOICE_STATUS = "update_invoice_status";
    public static final String NEW_PAYMENT = "new_payment";
    public static final String RESEND_EMAIL_INVOICE = "resend_email_invoice";
    public static final String ADD_BOOKING_TO_INVOICE = "add_booking_to_invoice";
    public static final String SEARCH_INVOICE = "search_invoice";

    public static final String NEW_INVOICE_RESPONSE = "new_invoice_response";
    public static final String UPDATE_INVOICE_RESPONSE = "update_invoice_response";
    public static final String GET_INVOICE_RESPONSE = "get_invoice_response";
    public static final String GET_ALL_INVOICE_RESPONSE = "get_all_invoice_response";
    public static final String GET_INVOICE_STATUS_RESPONSE = "get_invoice_status_response";
    public static final String UPDATE_INVOICE_STATUS_RESPONSE = "update_invoice_status_response";
    public static final String NEW_PAYMENT_RESPONSE = "new_payment_response";
    public static final String RESEND_EMAIL_INVOICE_RESPONSE = "resend_email_invoice_response";
    public static final String ADD_BOOKING_TO_INVOICE_RESPONSE = "add_booking_to_invoice_response";
    public static final String SEARCH_INVOICE_RESPONSE = "search_invoice_response";

    //Switch Agent ID
    public static final String SWITCH_AGENT_ID = "switch_agent_id";
    public static final String SWITCH_AGENT_ID_RESPONSE = "switch_agent_id_response";

    //payment destination
    public static final String CREATE_PAYMENT_DESTINATION = "create_payment_destination";
    public static final String UPDATE_PAYMENT_DESTINATION = "update_payment_destination";
    public static final String DELETE_PAYMENT_DESTINATION = "delete_payment_destination";
    public static final String GET_PAYMENT_DESTINATION     = "get_payment_destination";
    public static final String GET_ALL_PAYMENT_DESTINATION = "get_all_payment_destination";

    public static final String CREATE_PAYMENT_DESTINATION_RESPONSE = "create_payment_destination_response";
    public static final String UPDATE_PAYMENT_DESTINATION_RESPONSE = "update_payment_destination_response";
    public static final String DELETE_PAYMENT_DESTINATION_RESPONSE = "delete_payment_destination_response";
    public static final String GET_PAYMENT_DESTINATION_RESPONSE     = "get_payment_destination_response";
    public static final String GET_ALL_PAYMENT_DESTINATION_RESPONSE = "get_all_payment_destination_response";

    //payment order
    public static final String NEW_PAYMENT_ORDER = "new_payment_order";
    public static final String UPDATE_PAYMENT_ORDER = "update_payment_order";
    public static final String GET_PAYMENT_ORDER = "get_payment_order";
    public static final String GET_ALL_PAYMENT_ORDER = "get_all_payment_order";
    public static final String GET_PAYMENT_ORDER_STATUS = "get_payment_order_status";
    public static final String UPDATE_PAYMENT_ORDER_STATUS = "update_payment_order_status";
    public static final String RESEND_EMAIL_PAYMENT_ORDER = "resend_email_payment_order";
    public static final String DELETE_BOOKING_FROM_PAYMENT_ORDER = "delete_booking_from_payment_order";
    public static final String SEARCH_PAYMENT_ORDER = "search_payment_order";

    public static final String NEW_PAYMENT_ORDER_RESPONSE = "new_payment_order_response";
    public static final String UPDATE_PAYMENT_ORDER_RESPONSE = "update_payment_order_response";
    public static final String GET_PAYMENT_ORDER_RESPONSE = "get_payment_order_response";
    public static final String GET_ALL_PAYMENT_ORDER_RESPONSE = "get_all_payment_order_response";
    public static final String GET_PAYMENT_ORDER_STATUS_RESPONSE = "get_payment_order_status_response";
    public static final String UPDATE_PAYMENT_ORDER_STATUS_RESPONSE = "update_payment_order_status_response";
    public static final String RESEND_EMAIL_PAYMENT_ORDER_RESPONSE = "resend_email_payment_order_response";
    public static final String DELETE_BOOKING_FROM_PAYMENT_ORDER_RESPONSE = "delete_booking_from_payment_order_response";
    public static final String SEARCH_PAYMENT_ORDER_RESPONSE = "search_payment_order_response";

    //booking
    public static final String GET_BOOKING = "get_booking";
    public static final String GET_BOOKING_RESPONSE = "get_booking_response";
    public static final String BOOKING_SEARCH_BY_CRITERIA = "booking_search_by_criteria";
    public static final String BOOKING_SEARCH_BY_CRITERIA_RESPONSE = "booking_search_by_criteria_response";
    public static final String BOOKING_RECALCULATE = "booking_recalculate";
    public static final String BOOKING_RECALCULATE_RESPONSE = "booking_recalculate_response";

    //Ticket
    public static final String CREATE_TICKET_SUPPLIER = "create_ticket_supplier";
    public static final String CREATE_TICKET_SUPPLIER_RESPONSE = "create_ticket_supplier_response";
    public static final String DELETE_TICKET_SUPPLIER = "delete_ticket_supplier";
    public static final String DELETE_TICKET_SUPPLIER_RESPONSE = "delete_ticket_supplier_response";
    public static final String UPDATE_TICKET_SUPPLIER = "update_ticket_supplier";
    public static final String UPDATE_TICKET_SUPPLIER_RESPONSE = "update_ticket_supplier_response";

    public static final String LIST_ALL_TICKET_SUPPLIER = "list_all_ticket_supplier";
    public static final String LIST_ALL_TICKET_SUPPLIER_RESPONSE = "list_all_ticket_supplier_response";
    public static final String LIST_ALL_TICKET_GROUP = "list_all_ticket_group";
    public static final String LIST_ALL_TICKET_GROUP_RESPONSE = "list_all_ticket_group_response";

    public static final String CREATE_TICKET_CONTAINER = "create_ticket_container";
    public static final String CREATE_TICKET_CONTAINER_RESPONSE = "create_ticket_container_response";
    public static final String UPDATE_TICKET_CONTAINER = "update_ticket_container";
    public static final String UPDATE_TICKET_CONTAINER_RESPONSE = "update_ticket_container_response";
    public static final String DELETE_TICKET_CONTAINER = "delete_ticket_container";
    public static final String DELETE_TICKET_CONTAINER_RESPONSE = "delete_ticket_container_response";
    public static final String GET_TICKET_CONTAINER = "get_ticket_container";
    public static final String GET_TICKET_CONTAINER_RESPONSE = "get_ticket_container_response";
    public static final String GET_ALL_TICKET_CONTAINER = "get_all_ticket_container";
    public static final String GET_ALL_TICKET_CONTAINER_RESPONSE = "get_all_ticket_container_response";

    //Booking Email History
    public static final String SEARCH_EMAIL_SENT_HISTORY = "email_search";
    public static final String SEARCH_EMAIL_SENT_HISTORY_RESPONSE = "email_search_completed";

    public static final String CREATE_PAYEE = "create_payee";
    public static final String UPDATE_PAYEE = "update_payee";
    public static final String DELETE_PAYEE = "delete_payee";
    public static final String GET_PAYEE     = "get_payee";
    public static final String GET_ALL_PAYEE = "get_all_payee";

    public static final String CREATE_PAYEE_RESPONSE = "create_payee_response";
    public static final String UPDATE_PAYEE_RESPONSE = "update_payee_response";
    public static final String DELETE_PAYEE_RESPONSE = "delete_payee_response";
    public static final String GET_PAYEE_RESPONSE     = "get_payee_response";
    public static final String GET_ALL_PAYEE_RESPONSE = "get_all_payee_response";

}
